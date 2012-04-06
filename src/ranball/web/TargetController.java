package ranball.web;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ranball.domain.Cell;
import ranball.domain.Grid;
import ranball.domain.GridEntity;
import ranball.domain.Terrain;
import ranball.service.PermissionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TargetController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());
    
    private PermissionManager permissionManager;
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        Map<String, Object> myModel = new TreeMap<String, Object>();
		GridEntity gridEntity;
		String resp;

		if (request != null) {
			logger.info(request.getParameter("coord"));
		
			int type = -1;
			
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(request.getParameter("coord"));
				int terrainId = jsonObject.getInt("region");
				int cellId = jsonObject.getInt("number") + terrainId * 100;
				String userName = jsonObject.getString("name");
				Grid grid = this.permissionManager.getGrid(terrainId, userName);
				gridEntity = grid.getGridEntities().get(cellId / 10).get(cellId % 10);
				jsonObject = new JSONObject();
				jsonObject.put("type", gridEntity.getCellType());
				jsonObject.put("value", gridEntity.getCellValue());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				throw new IOException("������ ���  ������� ������ ������� \"" + 
						request.getParameter("coord") + 
						"\"");
			}
			resp = jsonObject.toString();
		}
		else {
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("type", -1);
				jsonObject.put("value", -1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp = jsonObject.toString();
		}
		
//		myModel.put("cells", terrain.getCells());

		return new ModelAndView("target", "response", resp);
    }

    public void setPermissionManager(PermissionManager permissionManager) {
        String answer = permissionManager == null ?
        		"terrainManager is null" : "terrainManager is'nt null";
        logger.info("*******************************\nInitializing start view: " + answer);
        this.permissionManager = permissionManager;
    }
}