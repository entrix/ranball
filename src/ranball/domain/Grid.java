package ranball.domain;

import java.util.Iterator;
import java.util.List;

public class Grid {

	public List<List<GridEntity>> gridEntities;

	public List<List<GridEntity>> getGridEntities() {
		return gridEntities;
	}

	public void setGridEntities(List<List<GridEntity>> gridEntities) {
		this.gridEntities = gridEntities;
	}

}