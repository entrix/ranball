package ranball.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import ranball.domain.InMemoryDefaultDefaultEntities;
import ranball.domain.Permission;
import ranball.domain.Terrain;
import ranball.domain.User;
import ranball.service.SimplePermissionManager;
import ranball.service.SimpleTerrainManager;
import ranball.web.TargetController;
import junit.framework.TestCase;

public class TargetControllerTests extends TestCase {

	TargetController controller;
	SimplePermissionManager simplePermissionManager;
	
    @Override
	protected void setUp() throws Exception {
    	controller = new TargetController();
		List<Terrain> terrains = new ArrayList<Terrain>(1);
		Terrain terrain = new Terrain();
		terrain.setCells(InMemoryDefaultDefaultEntities.getDefaultCells());
		terrains.add(terrain);
		simplePermissionManager = new SimplePermissionManager();
		simplePermissionManager.setTerrains(terrains);
		List<Permission> permissions = InMemoryDefaultDefaultEntities.getDefaultPermissions();
		simplePermissionManager.setPermissions(permissions);
		List<User> users = InMemoryDefaultDefaultEntities.getDefaultUsers();
		simplePermissionManager.setUsers(users);
    	controller.setPermissionManager(simplePermissionManager);
	}
    
    public void testHandleRequestView() throws Exception{		
    	ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("target", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        String resp = (String) modelAndView.getModel().get("response");
        assertNotNull(resp);
    }
}
