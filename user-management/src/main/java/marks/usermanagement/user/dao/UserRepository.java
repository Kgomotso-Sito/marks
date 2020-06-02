package marks.usermanagement.user.dao;

import marks.usermanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    User findByUserNumber(String userNumber);
    Optional<User> findById(Integer id);
    List<User> findUsersByRole(User.Role role);
    Integer countUserByRole(User.Role role);
    List<User> findUsersByIdIn(List<Integer> userIdList);
    List<User> findUsersByUserNumberIn(List<String> userNumbers);
}
