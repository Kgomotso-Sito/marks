package marks.usermanagement.user.service;

import marks.usermanagement.user.dao.UserRepository;
import marks.usermanagement.user.dto.UserList;
import marks.usermanagement.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUserNumber(String userNumber)  {
        User user = userRepository.findByUserNumber(userNumber);
        return user;
    }

    public User findByUserId(Integer userId)  {
        Optional<User> user = userRepository.findById(userId);
        return user.get();
    }

    public Integer getNumberOfUsers(User.Role user) {
        return userRepository.countUserByRole(user);
    }

    public User createOrUpdate(User user)  {
        if (user.getUserNumber() == null || user.getUserNumber().trim().equals("")) {
            String userNumber = String.format(user.getRole().toString().charAt(0) + "%d%04d",
                    Calendar.getInstance().get(Calendar.YEAR), getNumberOfUsers(user.getRole()));
            user.setUserNumber(userNumber);
        }
        return userRepository.saveAndFlush(user);
    }

    public boolean deactivateUser(String userNumber) {
        User user = findByUserNumber(userNumber);
        if(user == null) {
           return false;
        } else {
            user.setActive(Boolean.FALSE);
            createOrUpdate(user);
            return true;
        }
    }

    public UserList findAllAdmin(){ return new UserList(userRepository.findUsersByRole(User.Role.Admin));
    }

    public UserList findAllTeachers(){ return new UserList(userRepository.findUsersByRole(User.Role.Teacher));
    }

    public UserList findAllLearners(){ return new UserList(userRepository.findUsersByRole(User.Role.Learner));
    }

    public UserList findByUserNumber(List<String> userNumbers)  {
        return new UserList(userRepository.findUsersByUserNumberIn(userNumbers));
    }

}
