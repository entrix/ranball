package ranball.service;

import ranball.domain.Terrain;

public interface TerrainManager {

  public Terrain getGrid();

  public void makeShot(Integer cellNumber);
  
}