package ranball.service;

import java.util.ArrayList;
import java.util.List;

import ranball.domain.Grid;
import ranball.domain.GridEntity;
import ranball.domain.InMemoryDefaultDefaultEntities;
import ranball.domain.Permission;
import ranball.domain.Terrain;
import ranball.domain.User;
import junit.framework.TestCase;

public class SimplePermissionManagerTests extends TestCase {

	SimplePermissionManager simplePermissionManager;

	@Override
	protected void setUp() throws Exception {
		simplePermissionManager = new SimplePermissionManager();
//		terrain = new Terrain();
//		terrain.setCells(InMemoryDefaultCells.getDefaultCells());
	}

	public void testAllMethods() {
		List<Terrain> terrains = new ArrayList<Terrain>(1);
		Terrain terrain = new Terrain();
		terrain.setCells(InMemoryDefaultDefaultEntities.getDefaultCells());
		terrains.add(terrain);
		simplePermissionManager.setTerrains(terrains);
		List<Permission> permissions = InMemoryDefaultDefaultEntities.getDefaultPermissions();
		simplePermissionManager.setPermissions(permissions);
		List<User> users = InMemoryDefaultDefaultEntities.getDefaultUsers();
		simplePermissionManager.setUsers(users);
		
		Grid grid = simplePermissionManager.getGrid(0, "name-0");
		List<List<GridEntity>> gridEntities = grid.getGridEntities();
		for (int i = 0; i < gridEntities.size(); ++i) {
			for (int j = 0; j < gridEntities.get(0).size(); ++j) {
				int n = i*10 + j;
				if (n == 0 || n == 5 || n == 10) {
						assertEquals("simplePermissionManager methods failed " + n, new Integer(n)           , gridEntities.get(i).get(j).getCellId());
						assertEquals("simplePermissionManager methods failed " + n, new Integer(n - (n*2)%10), gridEntities.get(i).get(j).getCellValue());
					}
				}
		}
	}
}
