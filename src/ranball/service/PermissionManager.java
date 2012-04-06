package ranball.service;

import ranball.domain.Grid;

public interface PermissionManager {

  public Grid getGrid(Integer terrainId, String userName);

  public void makeShot(Integer cellNumber, Integer terrainNumber, String userName, Integer shotValue);

}