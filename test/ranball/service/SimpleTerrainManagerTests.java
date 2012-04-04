package ranball.service;

import ranball.domain.InMemoryDefaultCells;
import ranball.domain.Terrain;
import junit.framework.TestCase;

public class SimpleTerrainManagerTests extends TestCase {

	SimpleTerrainManager simpleTerrainManager;
	Terrain terrain;
	
	@Override
	protected void setUp() throws Exception {
		simpleTerrainManager = new SimpleTerrainManager();
		terrain = new Terrain();
		terrain.setCells(InMemoryDefaultCells.getDefaultCells());
	}

	public void testSetAndGetTerrain() {
		assertNull("impleTerrainManager.getTerrain method failed", simpleTerrainManager.getTerrain());
		simpleTerrainManager.setTerrain(terrain);
		assertEquals("SimpleTerrainManager.setTerrain method failed", terrain, simpleTerrainManager.getTerrain());
	}
	
	public void testSetGrid() {
		simpleTerrainManager.setTerrain(terrain);
		assertEquals("SimpleTerrainManager.setGrid method failed", terrain, simpleTerrainManager.getGrid());
	}
}
