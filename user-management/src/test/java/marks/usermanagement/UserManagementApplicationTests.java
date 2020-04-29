package marks.usermanagement;

import marks.usermanagement.user.dao.UserRepository;
import marks.usermanagement.user.entity.User;
import marks.usermanagement.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserManagementApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	public void createAndSearchUser() {
		User testUser = new User();

		testUser.setRole(User.Role.Teacher);
		testUser.setTitle("Mr");
		testUser.setFullName("Teacher");
		testUser.setLastName("Test");

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

		userService.createOrUpdate(testUser);
		User user = userService.loadUserByLastName("Sito");

		assertEquals(testUser.getIdNumber(), user.getIdNumber());
	}
}
