package ranball.repository;

import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import ranball.domain.Cell;

public class JdbcCellDaoTests extends AbstractTransactionalDataSourceSpringContextTests {

    private CellDao cellDao;

    
    public void setCellDao(CellDao cellDao) {
        this.cellDao = cellDao;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] {"classpath:test-context.xml"};
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.deleteFromTables(new String[] {"cells"});
        super.executeSqlScript("file:db/load_cells.sql", true);
    }

    public void testGetProductList() {
        
        List<List<Cell>> cells = cellDao.getCellList(0);
        
        assertEquals("CellDao.getCellList method failed", 10, cells.size());
        assertEquals("CellDao.getCellList method failed", 10, cells.get(0).size());
    }
    
    public void testSaveCell() {
        
        List<List<Cell>> cells = cellDao.getCellList(0);
        
        for (List<Cell> cellList : cells) {
        	for (Cell cell : cellList) {
        		cell.setCoord(100);
        		cellDao.saveCell(cell);
        	}
        }
        
        cells = cellDao.getCellList(0);
        for (List<Cell> cellList : cells) {
        	for (Cell cell : cellList) {
        		assertEquals("wrong price of product?", new Integer(100),cell. getCoord());
        	}
        }

    }

}
