package ranball.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import ranball.domain.User;

public class JdbcUserDao extends SimpleJdbcDaoSupport implements UserDao {

	public List<User> getUserList() {
		return getSimpleJdbcTemplate().query(
	    		"select id, name, rating from users", 
                new UserMapper());
	}

	public User findById(Integer userId) {
		String sql = "select * from advection" +
		" where id_advection = :idAdvection";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", userId);
		User user = null;

		try {
			user = getSimpleJdbcTemplate().queryForObject(sql, new UserMapper(), namedParameters);
		} catch (EmptyResultDataAccessException enmptyException) {
			return null;
		} catch (DataAccessException dataException) {
			//    return null;
			System.out.println(dataException.getMessage());
			System.exit(-1);
		}

		return user;
}
	
	public List<User> findByName(String userName) {
		return getSimpleJdbcTemplate().query(
	    		"select id, name, rating from users where name = ?", 
                new UserMapper(),
                userName);
	}
	
	public void saveUser(User user) {
        logger.info("Saving user rating: " + user.getRating());
        int count = getSimpleJdbcTemplate().update(
            "update users set rating = :rating where id = :id",
            new MapSqlParameterSource().addValue("rating", user.getRating())
                .addValue("id", user.getId()));
        logger.info("Rows affected: " + count);
	}
	
    private static class UserMapper implements ParameterizedRowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        	User user = new User();
        	user.setId(rs.getInt("id"));
        	user.setName(rs.getString("name"));
        	user.setRating(rs.getInt("rating"));
            return user;
        }

    }
}