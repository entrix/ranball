package ranball.web;

import org.springframework.web.servlet.ModelAndView;

import ranball.domain.Terrain;
import ranball.service.SimpleTerrainManager;
import ranball.web.TargetController;
import junit.framework.TestCase;

public class TargetControllerTests extends TestCase {

	TargetController controller;
	SimpleTerrainManager simpleTerrainManager;
	
    @Override
	protected void setUp() throws Exception {
    	controller = new TargetController();
    	simpleTerrainManager = new SimpleTerrainManager();
    	simpleTerrainManager.setTerrain(new Terrain());
    	controller.setTerrainManager(simpleTerrainManager);
	}
    
    public void testHandleRequestView() throws Exception{		
    	ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("target", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        String resp = (String) modelAndView.getModel().get("response");
        assertNotNull(resp);
    }
}
