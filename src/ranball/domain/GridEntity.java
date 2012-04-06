package ranball.domain;

public class GridEntity {

	private Integer cellId;

	private Integer cellType;

	public Integer cellValue;

	public Integer getCellId() {
		return cellId;
	}

	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	public Integer getCellType() {
		return cellType;
	}

	public void setCellType(Integer cellType) {
		this.cellType = cellType;
	}

	public Integer getCellValue() {
		return cellValue;
	}

	public void setCellValue(Integer cellValue) {
		this.cellValue = cellValue;
	}

}