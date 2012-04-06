package ranball.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import ranball.domain.Permission;

public class JdbcPermissionDao extends SimpleJdbcDaoSupport implements PermissionDao {

	public List<Permission> getPermissionList(Integer terrainId, String userId) {
		return getSimpleJdbcTemplate().query(
	    		"select cell_id, terrain_id, user_id, cell_value from Permissions" +
	    		"where terrain_id = ? and user_id = ?", 
                new PermissionMapper(),
                terrainId,
                userId);
	}
	
	public void savePermission(Permission permission) {
		logger.info("Saving cell value: " + permission.getCellValue());
		int count = getSimpleJdbcTemplate().update(
				"update users set cell_value = :cellValue where cell_id = :cellId",
				new MapSqlParameterSource().addValue("cellValue", permission.getCellValue())
				.addValue("cellId", permission.getCellId()));
		logger.info("Rows affected: " + count);
	}
	
    private static class PermissionMapper implements ParameterizedRowMapper<Permission> {

        public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Permission Permission = new Permission();
        	Permission.setCellId(rs.getInt("cell_id"));
        	Permission.setTerrainId(rs.getInt("terrain_id"));
        	Permission.setUserId(rs.getInt("user_id"));
        	Permission.setCellValue(rs.getInt("cell_value"));
        	return Permission;
        }

    }
}