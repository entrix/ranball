package ranball.domain;

import java.util.List;
import junit.framework.TestCase;

public class TerrainTests extends TestCase {

	private Terrain terrain;
	private List<List<Cell>> cells;
	
	protected void setUp() throws Exception {
		terrain = new Terrain();
		cells = InMemoryDefaultCells.getDefaultCells();
		terrain.setCells(cells);
	}

	public void testGetAndSetWithNoCells() {
		terrain = new Terrain();
		assertNull("Terrain.getCells() method failed", terrain.getCells());
	}
	
	public void testGetAndSetCells() {
		cells = terrain.getCells();
		assertNotNull("Terrain.getCells() method failed", cells);
		assertEquals("Terrain.setCells method failed", true, InMemoryDefaultCells.checkDefaultCells(cells));
	}
	
	public void testGetAndSetCell() {
		Cell cell = terrain.getCell(1, 1);
		assertNotNull("Terrain.getCell(int, int) method failed", cell);
		
		cell.setId(0);
		cell.setType(0);
		cell.setCoord(0);
		terrain.setCell(1, 1, cell);
		cell = terrain.getCell(1, 1);
		assertNotNull("Terrain.setCell(int, int, Cell) method failed", cell);
		assertEquals("Terrain.setCell(int, int, Cell) method failed", new Integer(0), cell.getId());
		assertEquals("Terrain.setCell(int, int, Cell) method failed", new Integer(0), cell.getType());
		assertEquals("Terrain.setCell(int, int, Cell) method failed", new Integer(0), cell.getCoord());
		
		terrain = new Terrain(); 
		cell = terrain.getCell(1, 1);
		assertNull("Terrain.getCell(int, int) method failed", cell);
	}
}
