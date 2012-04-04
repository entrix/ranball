package ranball.service;

import ranball.domain.Terrain;

public class SimpleTerrainManager implements TerrainManager {

	private Terrain terrain;

	public Terrain getTerrain() {
		return terrain;
	}
	
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	@Override
	public Terrain getGrid() {
		return getTerrain();
	}

	@Override
	public void makeShot(Integer cellNumber) {
		// TODO Auto-generated method stub
		
	}
	
}