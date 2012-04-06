package ranball.service;

import ranball.domain.Terrain;

public class SimpleTerrainManager {

	private Terrain terrain;

	public Terrain getTerrain() {
		return terrain;
	}
	
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public Terrain getGrid() {
		return getTerrain();
	}

	public void makeShot(Integer cellNumber) {
		// TODO Auto-generated method stub
		
	}
	
}