package ranball.domain;

public class Permission {

	private Integer userId;

	private Integer cellId;

	private Integer terrainId;

	private Integer cellValue;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCellId() {
		return cellId;
	}
	
	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	public Integer getTerrainId() {
		return terrainId;
	}

	public void setTerrainId(Integer terrainId) {
		this.terrainId = terrainId;
	}

	public Integer getCellValue() {
		return cellValue;
	}

	public void setCellValue(Integer cellValue) {
		this.cellValue = cellValue;
	}

}