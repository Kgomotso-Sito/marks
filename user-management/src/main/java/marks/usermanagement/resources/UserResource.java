package marks.usermanagement.resources;

import marks.usermanagement.user.dto.UserList;
import marks.usermanagement.user.entity.User;
import marks.usermanagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String home() {
        return "User management micro-service is running";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean createUser(@RequestBody User user){
        return userService.createOrUpdate(user) != null;
    }

    @RequestMapping("/{userNumber}")
    public User getUserByUserNumber(@PathVariable("userNumber") String userNumber) {
        return userService.findByUserNumber(userNumber);
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

    @RequestMapping(path = "/deactivate/{userNumber}", method = RequestMethod.POST)
    public String deactivateUser(@PathVariable("userNumber") String userNumber) {
        return userService.deactivateUser(userNumber);
    }

}
