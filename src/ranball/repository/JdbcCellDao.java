package ranball.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import ranball.domain.Cell;

public class JdbcCellDao extends SimpleJdbcDaoSupport implements CellDao {

    protected final Log logger = LogFactory.getLog(getClass());
    
	public List<List<Cell>> getCellList(Integer terrainId) {
        logger.info("Getting products!");
        List<Cell> cellList = getSimpleJdbcTemplate().query(
                "select id, coord, type from cells where terrain_id = ?", 
                new CellMapper(),
                terrainId);
        List<List<Cell>> cells = new ArrayList<List<Cell>>(10);
        
        for (int i = 0; i < 10; ++i) {
        	cells.add(new ArrayList<Cell>(10));
        	for (int j = 0; j < 10; ++j) {
        		cells.get(i).add(cellList.get(i*10 + j + terrainId*100));
        	}
        }
        
        return cells;
	}

	public void saveCell(Cell cell) {
        logger.info("Saving cell in coord: " + cell.getCoord());
        int count = getSimpleJdbcTemplate().update(
            "update cells set type = :type where id = :id",
            new MapSqlParameterSource().addValue("type", cell.getType())
                .addValue("id", cell.getId()));
        logger.info("Rows affected: " + count);
	}
    
    private static class CellMapper implements ParameterizedRowMapper<Cell> {

        public Cell mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Cell cell = new Cell();
        	cell.setId(rs.getInt("id"));
        	cell.setType(rs.getInt("type"));
        	cell.setCoord(rs.getInt("coord"));
            return cell;
        }

    }

}