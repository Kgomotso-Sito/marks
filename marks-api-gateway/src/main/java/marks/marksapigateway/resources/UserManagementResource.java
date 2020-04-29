package marks.marksapigateway.resources;

import marks.marksapigateway.models.User;
import marks.marksapigateway.models.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserManagementResource {

    private String URL = "http://user-management-service/";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean createUser(@RequestBody User user){

        ResponseEntity<String> result = restTemplate.postForEntity(URL + "/create", user, String.class);

        if(result.getStatusCodeValue() == 201) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable("id") String id) {
        User user = restTemplate.getForObject(URL + "/find/" + id, User.class);
        return user;
    }

    @RequestMapping(path = "/findAll/{role}", produces = "application/json")
    public List<User> findAllByRole(@PathVariable("role") String role) {
        UserList userList = restTemplate.getForObject(URL + "/users/all/" + role, UserList.class);
        return userList.getUsers();
    }
}
