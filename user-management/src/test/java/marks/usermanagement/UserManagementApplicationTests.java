package marks.usermanagement;

import marks.usermanagement.user.dao.UserRepository;
import marks.usermanagement.user.dto.UserList;
import marks.usermanagement.user.entity.User;
import marks.usermanagement.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserManagementApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	public void createUser() {
		User testUser = new User();

		testUser.setRole(User.Role.Learner);
		testUser.setTitle("Mr");
		testUser.setFullName("Kgomotso");
		testUser.setLastName("Sito");

		testUser.setGender("Male");
		testUser.setRace("Black");
		testUser.setBirthDate(new Date());

		testUser.setNationality("South Africa");
		testUser.setIdNumber("940221679887601");

		testUser.setEmailAddress("khomcy.sito@gmail.com");
		testUser.setPhoneNumber("012345679");

		testUser.setHouseNo("233");
		testUser.setStreetNo("Archilees");
		testUser.setCity("Pretoria");
		testUser.setProvince("Gauteng");

		User user = userService.createOrUpdate(testUser);

		assertTrue(user.equals(testUser));
	}

	@Test
	public void searchUser() {
		User user = userService.findByUserNumber("L20200000");
		assertEquals("L20200000", user.getUserNumber());
	}


	@Test
	public void searchUsers() {
		UserList users = userService.findByUserNumber(Arrays.asList("L20200000"));
		assertTrue(users.getUsers().size() == 1);
	}

	@Test
	public void searchById() {
		User user = userService.findByUserId(1);
		assertTrue(user != null && user.getId() == 1);
	}

	@Test
	public void searchByRoleAdmin() {
		UserList users = userService.findAllAdmin();
		assertTrue(users.getUsers().stream().allMatch(user -> user.getRole().equals(User.Role.Admin)));
	}

	@Test
	public void searchByRoleTeacher() {
		UserList users = userService.findAllTeachers();
		assertTrue(users.getUsers().stream().allMatch(user -> user.getRole().equals(User.Role.Teacher)));
	}

	@Test
	public void searchByRoleLearner() {
		UserList users = userService.findAllLearners();
		assertTrue(users.getUsers().stream().allMatch(user -> user.getRole().equals(User.Role.Learner)));
	}

}
