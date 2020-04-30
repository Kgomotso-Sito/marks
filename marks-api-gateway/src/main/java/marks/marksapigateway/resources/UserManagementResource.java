package marks.marksapigateway.resources;

import marks.marksapigateway.models.user.User;
import marks.marksapigateway.models.user.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserManagementResource {

    private String URL = "http://user-management-service/users";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean createUser(@RequestBody User user){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(URL + "/create", entity, String.class);

        return (result.getStatusCodeValue() == 201);
    }

    @RequestMapping(path = "/all/{role}", produces = "application/json")
    public List<User> findAllByRole(@PathVariable("role") String role) {
        UserList userList = restTemplate.getForObject(URL + "/all/" + role, UserList.class);
        return userList.getUsers();
    }

    @RequestMapping(path = "/{userNumber}", method = RequestMethod.GET)
    public User findUserByUserNumber(@PathVariable("userNumber") String userNumber) {
        User user = restTemplate.getForObject(URL + "/" + userNumber, User.class);
        return user;
    }

    @RequestMapping(path = "/deactivate/{userNumber}")
    public String deactivateUser(@PathVariable("userNumber") String userNumber) {
        return restTemplate.postForObject(URL + "/deactivate/" + userNumber, null ,String.class);
    }
}
