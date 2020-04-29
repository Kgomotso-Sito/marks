package marks.usermanagement.user.service;

import marks.usermanagement.user.dao.UserRepository;
import marks.usermanagement.user.dto.UserList;
import marks.usermanagement.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User loadUserByLastName(String lastName)  {
        User user = userRepository.findByLastName(lastName);
        return user;
    }

    public void createOrUpdate(User user)  {
        userRepository.saveAndFlush(user);
    }

    public UserList findAllAdmin(){ return new UserList(userRepository.findUsersByRole(User.Role.Admin));
    }

    public UserList findAllTeachers(){ return new UserList(userRepository.findUsersByRole(User.Role.Teacher));
    }

    public UserList findAllLearners(){ return new UserList(userRepository.findUsersByRole(User.Role.Learner));
    }
}
