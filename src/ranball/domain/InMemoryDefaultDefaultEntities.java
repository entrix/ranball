package ranball.domain;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDefaultDefaultEntities {

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
	
	public static List<User> getDefaultUsers() {
		List<User> users = new ArrayList<User>(10);
		for (int i = 0; i < 5; ++i) {
			User user = new User();
			user.setId(i);
			user.setName("name-" + i);
			user.setRating(i);
			users.add(user);
		}
		
		return users;
	}
	
	public static boolean checkDefaultUsers(List<User> users) {
		for (int i = 0; i < 10; ++i) {
			User user = users.get(i);
			if (user.getId() != i) {
				return false;
			}
			if (!user.getName().equals("name-" + i)) {
				return false;
			}
			if (user.getRating() != i) {
				return false;
			}
		}
		
		return true;
	}
	
	public static List<Permission> getDefaultPermissions() {
		List<Permission> permissions = new ArrayList<Permission>(10);
		for (int i = 0; i < 15; ++i) {
			Permission permission = new Permission();
			permission.setCellId(i);
			permission.setTerrainId(i%5);
			permission.setUserId(i%5);
			permission.setCellValue(i - (i*2)%10);
			permissions.add(permission);
		}
		
		return permissions;
	}
	
	public static boolean checkDefaultPermissions(List<Permission> permissions) {
		for (int i = 0; i < 10; ++i) {
			Permission permission = permissions.get(i);
			if (permission.getCellId() != i) {
				return false;
			}
			if (permission.getTerrainId() != i%10) {
				return false;
			}
			if (permission.getUserId() != i%5) {
				return false;
			}
			if (permission.getCellValue() != (i - (i*2)%10)) {
				return false;
			}
		}
		
		return true;
	}
}
