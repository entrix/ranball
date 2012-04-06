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
import ranball.repository.CellDao;
import ranball.repository.PermissionDao;
import ranball.repository.UserDao;

public class SimplePermissionManager implements PermissionManager {

//	protected final Log logger = LogFactory.getLog(getClass());
	
	private CellDao cellDao;
	private UserDao userDao;
	private PermissionDao permissionDao;

	public Grid getGrid(Integer terrainId, String userName) {
		List<List<Cell>> cells = cellDao.getCellList(terrainId);
		Integer userId = null;
		
		for (User user : userDao.getUserIlst()) {
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
		
		for (Permission permission : permissionDao.getPermissionList()) {
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
		
		for (User user : userDao.getUserIlst()) {
			if (user.getName().equals(userName)) {
				userId = user.getId();
			}
		}
		
		for (Permission permission : permissionDao.getPermissionList()) {
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
		permissionDao.savePermission(permission);
	}

	public void setCellDao(CellDao cellDao) {
		this.cellDao = cellDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

}