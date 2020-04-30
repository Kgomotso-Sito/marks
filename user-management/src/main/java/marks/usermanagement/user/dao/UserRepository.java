package marks.usermanagement.user.dao;

import marks.usermanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Integer> {
    User findByUserNumber(String userNumber);
    List<User> findUsersByRole(User.Role role);
    Integer countUserByRole(User.Role role);
}
