package marks.usermanagement.resources;

import marks.usermanagement.user.dto.UserList;
import marks.usermanagement.user.entity.User;
import marks.usermanagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String home() {
        return "User management micro-service is running";
    }

    @RequestMapping("/lastName")
    public User getUserByLastName() {
        return userService.loadUserByLastName("Sito");
    }

    @RequestMapping("/all/{role}")
    public UserList getAllUsersByRole(@PathVariable("role") String role){
        try {
            User.Role userRole = User.Role.valueOf(role);
            switch (userRole) {
                case Admin:
                    return userService.findAllAdmin();
                case Teacher:
                    return userService.findAllTeachers();
                case Learner:
                    return userService.findAllLearners();
            }
        } catch (IllegalArgumentException e) {
            // log error or something here
        }
        return null;
    }

}
