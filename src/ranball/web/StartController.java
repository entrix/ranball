package ranball.web;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ranball.domain.Grid;
import ranball.domain.GridEntity;
import ranball.service.PermissionManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StartController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());

    private PermissionManager permissionManager;
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	Map<String, Object> myModel = new TreeMap<String, Object>();
		List<List<GridEntity>> gridEntities = null;

		if (request != null) {
			logger.info("user: " + request.getParameter("name")
					+ ", region: " + request.getParameter("region"));
			int terrainId = Integer.parseInt(request.getParameter("region"));
			String userName = request.getParameter("name");
			Grid grid = this.permissionManager.getGrid(terrainId, userName);
			gridEntities = grid.getGridEntities();
		}
		
		myModel.put("gridEntities", gridEntities);

		return new ModelAndView("start", "model", myModel);
    }

    public void setPermissionManager(PermissionManager permissionManager) {
        String answer = permissionManager == null ?
        		"terrainManager is null" : "terrainManager is'nt null";
        logger.info("*******************************\nInitializing start view: " + answer);
        this.permissionManager = permissionManager;
    }
}
