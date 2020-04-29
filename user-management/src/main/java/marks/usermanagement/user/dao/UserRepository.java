package marks.usermanagement.user.dao;

import marks.usermanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Integer> {
    User findByLastName(String lastName);
    List<User> findUsersByRole(User.Role role);
}
