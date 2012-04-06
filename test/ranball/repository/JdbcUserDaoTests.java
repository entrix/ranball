package ranball.repository;

import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import ranball.domain.User;

public class JdbcUserDaoTests extends AbstractTransactionalDataSourceSpringContextTests {

    private UserDao userDao;

    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] {"classpath:test-context.xml"};
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.deleteFromTables(new String[] {"users"});
        super.executeSqlScript("file:db/load_users.sql", true);
    }

    public void testGetUserList() {
        
        List<User> Users = userDao.getUserList();
        
        assertEquals("wrong number of Users?", 3, Users.size());
        
    }
    
    public void testSaveUser() {
        
        List<User> Users = userDao.getUserList();
        
        for (User user : Users) {
            user.setRating(100);
            userDao.saveUser(user);
        }
        
        List<User> updatedUsers = userDao.getUserList();
        for (User user : updatedUsers) {
            assertEquals("wrong price of User?", new Integer(100), user.getRating());
        }

    }

}
