package ranball.web;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ranball.domain.Terrain;
import ranball.service.TerrainManager;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class PrintController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());
    
    private TerrainManager terrainManager;
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> myModel = new TreeMap<String, Object>();
        Terrain terrain = this.terrainManager.getGrid();
        
        myModel.put("cells", terrain.getCells());

        return new ModelAndView("print", "model", myModel);
    }

    public void setTerrainManager(TerrainManager terrainManager) {
        String answer = terrainManager == null ?
        		"terrainManager is null" : "terrainManager is'nt null";
        logger.info("*******************************\nInitializing start view: " + answer);
        this.terrainManager = terrainManager;
    }
}