package ranball.repository;

import java.util.List;

import ranball.domain.User;

public interface  UserDao {

	public List<User> getUserList();

	public void saveUser(User user);

}