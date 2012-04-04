package ranball.domain;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDefaultCells {

	public static List<List<Cell>> getDefaultCells() {
		List<List<Cell>> cells = new ArrayList<List<Cell>>(10);
		for (int i = 0; i < 10; ++i) {
			cells.add(new ArrayList<Cell>(10));
			for (int j = 0; j < 10; ++j) {
				Cell cell = new Cell();
				cell.setId(i*10 + j);
				cell.setCoord(i*10 + j);
				cell.setType(j);
				cells.get(i).add(cell);
			}
		}
		return cells;
	}
	
	public static boolean checkDefaultCells(List<List<Cell>> cells) {
		for (int i = 0; i < 10; ++i) {
			List<Cell> cellList = cells.get(i);
			for (int j = 0; j < 10; ++j) {
				Cell cell = cellList.get(j);
				if (cell.getId() != i*10 + j) {
					return false;
				}
				if (cell.getCoord() != i*10 + j) {
					return false;
				}
				if (cell.getType() != j) {
					return false;
				}
			}
		}
		
		return true;
	}
}
