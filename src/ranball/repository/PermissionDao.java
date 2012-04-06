package ranball.repository;

import java.util.List;

import ranball.domain.Permission;

public interface  PermissionDao {

	public List<Permission> getPermissionList(Integer terrainId, Integer userId);

	public void savePermission(Permission permission);

}