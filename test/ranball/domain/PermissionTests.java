package ranball.domain;

import junit.framework.TestCase;

public class PermissionTests extends TestCase {
	
	Permission permission;
	
	@Override
	protected void setUp() throws Exception {
		permission = new Permission();
	}

	public void testGetAndSetCellId() {
		Integer cellId = permission.getCellId();
		assertNull("Permission.getCellId() method failed", cellId);
		cellId = 1;
		permission.setCellId(cellId);
		assertEquals("Permission.setCellId() method failed", new Integer(1), permission.getCellId());
	}
	
	public void testGetAndSetTerrainId() {
		Integer TerrainId = permission.getTerrainId();
		assertNull("Permission.getTerrainId() method failed", TerrainId);
		TerrainId = 1;
		permission.setTerrainId(TerrainId);
		assertEquals("Permission.setTerrainId() method failed", new Integer(1), permission.getTerrainId());
	}
	
	public void testGetAndSetUserId() {
		Integer UserId = permission.getUserId();
		assertNull("Permission.getUserId() method failed", UserId);
		UserId = 1;
		permission.setUserId(UserId);
		assertEquals("Permission.setUserId() method failed", new Integer(1), permission.getUserId());
	}
	
	public void testGetAndSetCellValue() {
		Integer cellValue = permission.getCellValue();
		assertNull("Permission.getCellValue() method failed", cellValue);
		cellValue = 1;
		permission.setCellValue(cellValue);
		assertEquals("Permission.setCellValue() method failed", new Integer(1), permission.getCellValue());
	}
}
