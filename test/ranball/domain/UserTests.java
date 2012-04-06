package ranball.domain;

import junit.framework.TestCase;

public class UserTests extends TestCase {

	private User user;
	
	protected void setUp() throws Exception {
		user = new User();
	}

	public void testGetAndSetId() {
		Integer id = user.getId();
		assertNull("User.getId method failed", id);
		id = 1;
		user.setId(id);
		assertEquals("User.setId method failed", new Integer(1), user.getId());
	}
	
	public void testGetAndSetName() {
		String name = user.getName();
		assertNull("User.getName method failed", name);
		name = "first";
		user.setName(name);
		assertEquals("User.setName method failed", new String("first"), user.getName());
	}
	
	public void testGetAndSetRating() {
		Integer rating = user.getRating();
		assertNull("User.getRating method failed", rating);
		rating = 1;
		user.setRating(rating);
		assertEquals("User.setRating method failed", new Integer(1), user.getRating());
	}
	
}
