package ranball.domain;

import junit.framework.TestCase;

public class CellTests extends TestCase {

	private Cell cell;
	
	@Override
	protected void setUp() throws Exception {
		cell = new Cell();
	}

	public void testSetAndGetId() {
		Integer id = 1;
		assertNull("Cell.getId() method failed", cell.getId());
		cell.setId(id);
		assertEquals("Cell.setId() method failed", id, cell.getId());
	}
	
	public void testSerAndGetCoord() {
		Integer coord = 1;
		assertNull("getCoord() method failed", cell.getCoord());
		cell.setCoord(coord);
		assertEquals("setCoord() method failed", coord, cell.getCoord());
	}
	
	public void testSerAndGetType() {
		Integer type = 1;
		assertNull("getType() method failed", cell.getType());
		cell.setType(type);
		assertEquals("setType() method failed", type, cell.getType());
	}
}
