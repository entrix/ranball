package ranball.repository;

import java.util.List;

import ranball.domain.Cell;

public interface CellDao {

  public List<List<Cell>> getCellList(Integer terrainId);

  public void saveCell(Cell cell);

}