package ranball.repository;

import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import ranball.domain.Permission;

public class JdbcPermissionDaoTests extends AbstractTransactionalDataSourceSpringContextTests {

    private PermissionDao PermissionDao;

    
    public void setPermissionDao(PermissionDao PermissionDao) {
        this.PermissionDao = PermissionDao;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] {"classpath:test-context.xml"};
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.deleteFromTables(new String[] {"Permissions"});
        super.executeSqlScript("file:db/load_permissions.sql", true);
    }

    public void testGetPermissionList() {
        
        List<Permission> Permissions = PermissionDao.getPermissionList();
        
        assertEquals("wrong number of Permissions?", 3, Permissions.size());
        
    }
    
    public void testSavePermission() {
        
        List<Permission> Permissions = PermissionDao.getPermissionList();
        
        for (Permission Permission : Permissions) {
            Permission.setCellValue(10);
            PermissionDao.savePermission(Permission);
        }
        
        List<Permission> updatedPermissions = PermissionDao.getPermissionList();
        for (Permission Permission : updatedPermissions) {
            assertEquals("wrong price of Permission?", new Integer(10), Permission.getCellValue());
        }

    }

}