package ranball.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ranball.domain.Cell;
import ranball.domain.Grid;
import ranball.domain.GridEntity;
import ranball.domain.Permission;
import ranball.domain.User;
import ranball.domain.Terrain;

public class SimplePermissionManager implements PermissionManager {

//	protected final Log logger = LogFactory.getLog(getClass());
	
	private List<Terrain> terrains;
	private List<User> users;
	private List<Permission> permissions;

	public Grid getGrid(Integer terrainId, String userName) {
		Terrain terrain = terrains.get(terrainId);
		List<List<Cell>> cells = terrain.getCells();
		Integer userId = null;
		
		for (User user : users) {
			if (user.getName().equals(userName)) {
				userId = user.getId();
			}
		}
	
		List<List<GridEntity>> gridEntities = new ArrayList<List<GridEntity>>();
		
		for (int i = 0; i < cells.size(); ++i) {
			gridEntities.add(new ArrayList<GridEntity>(cells.get(0).size()));
			for (int j = 0; j < cells.get(0).size(); ++j) {
				GridEntity gridEntity = new GridEntity();
				gridEntity.setCellId(cells.get(i).get(j).getId());
				gridEntity.setCellType(cells.get(i).get(j).getType());
				gridEntity.setCellValue(0);
				gridEntities.get(i).add(gridEntity);
			}
		}
		
		for (Permission permission : permissions) {
			if(permission.getTerrainId() == terrainId && permission.getUserId() == userId) {
				int n = permission.getCellId();
//				logger.info("permission index cellId = " + n);
				gridEntities.get(n / 10).get(n % 10).setCellValue(permission.getCellValue());
			}
		}	
		
		Grid grid = new Grid();
		grid.setGridEntities(gridEntities);
		return grid;
	}

	public void makeShot(Integer cellNumber, Integer terrainId, String userName, Integer shotValue) {
		Integer userId = null;
		
		for (User user : users) {
			if (user.getName().equals(userName)) {
				userId = user.getId();
			}
		}
		
		for (Permission permission : permissions) {
			if(permission.getTerrainId() == terrainId && permission.getUserId() == userId) {
				int n = permission.getCellId();
				//
				if ((n - terrainId * 100) == cellNumber) {
					permission.setCellValue(shotValue);
					return;
				}
			}
		}
		
		Permission permission = new Permission();
		
		permission.setCellId(cellNumber + terrainId * 100);
		permission.setTerrainId(terrainId);
		permission.setUserId(userId);
		permission.setCellValue(shotValue);
		permissions.add(permission);
	}

	public void setTerrains(List<Terrain> terrains) {
		this.terrains = terrains;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}