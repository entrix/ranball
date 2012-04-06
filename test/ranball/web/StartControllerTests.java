package ranball.web;

import org.springframework.web.servlet.ModelAndView;

import ranball.domain.Terrain;
import ranball.service.SimpleTerrainManager;
import ranball.web.TargetController;
import junit.framework.TestCase;

public class StartControllerTests extends TestCase {

    public void testHandleRequestView() throws Exception{
    	StartController controller = new StartController();
    	ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("start", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
    }
}