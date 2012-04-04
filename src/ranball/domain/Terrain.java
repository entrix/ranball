package ranball.domain;

import java.util.List;

public class Terrain {

	private List<List<Cell>> cells;

	private Integer width;

	private Integer height;

	//    public Vector  1..n;

	public List<List<Cell>> getCells() {
		return cells;
	}

	public void setCells(List<List<Cell>> cells) {
		this.cells = cells;
		this.width = cells.size();
		this.height = cells.get(0).size();
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public Cell getCell(int i, int j) {
		if (width != null  && height != null &&
			i     <  width && j       < height) {
			return cells.get(i).get(j); 
		}
		
		return null;
	}
	
	public void setCell(int i, int j, Cell cell) {
		if (width != null  && height != null &&
			i     <  width && j       < height) {
			cells.get(i).set(j, cell);
		}
	}
}