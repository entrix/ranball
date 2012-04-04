package ranball.web;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import ranball.domain.Terrain;
import ranball.service.SimpleTerrainManager;
import ranball.web.PrintController;

import junit.framework.TestCase;

public class PrintControllerTests extends TestCase {

	PrintController controller;
	SimpleTerrainManager simpleTerrainManager;
	
    @Override
	protected void setUp() throws Exception {
    	controller = new PrintController();
    	simpleTerrainManager = new SimpleTerrainManager();
    	simpleTerrainManager.setTerrain(new Terrain());
    	controller.setTerrainManager(simpleTerrainManager);
	}

	public void testHandleRequestView() throws Exception {		
    	ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("print", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
//        Map modelMap = (Map) modelAndView.getModel().get("model");
    }
}
