package marks.usermanagement.user.service;

import marks.usermanagement.user.dao.UserRepository;
import marks.usermanagement.user.dto.UserList;
import marks.usermanagement.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUserNumber(String userNumber)  {
        User user = userRepository.findByUserNumber(userNumber);
        return user;
    }

    public Integer getNumberOfUsers(User.Role user) {
        return userRepository.countUserByRole(user);
    }

    public User createOrUpdate(User user)  {
        if (user.getUserNumber() == null) {
            String userNumber = String.format(user.getRole().toString().charAt(0) + "%d%04d",
                    Calendar.getInstance().get(Calendar.YEAR), getNumberOfUsers(user.getRole()));
            user.setUserNumber(userNumber);
        }
        return userRepository.saveAndFlush(user);
    }

    public String deactivateUser(String userNumber) {
        User user = findByUserNumber(userNumber);
        if(user == null) {
            return "User not exist";
        } else {
            user.setActive(Boolean.FALSE);
            createOrUpdate(user);
            return "User deactivated";
        }
    }

    public UserList findAllAdmin(){ return new UserList(userRepository.findUsersByRole(User.Role.Admin));
    }

    public UserList findAllTeachers(){ return new UserList(userRepository.findUsersByRole(User.Role.Teacher));
    }

    public UserList findAllLearners(){ return new UserList(userRepository.findUsersByRole(User.Role.Learner));
    }
}
