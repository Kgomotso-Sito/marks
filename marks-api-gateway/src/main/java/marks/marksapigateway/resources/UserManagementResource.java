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

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*", maxAge=3600)
public class UserManagementResource {

    private String URL = "https://user-management-1591161650651.herokuapp.com/users";
    private String userSubjectURL = "https://subject-1591161650651.herokuapp.com/usersubject";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json")
    public boolean createUser(@RequestBody User user){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(URL + "/create", entity, String.class);

        return (result.getStatusCodeValue() == 201 || result.getStatusCodeValue() == 200);
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
    public boolean deactivateUser(@PathVariable("userNumber") String userNumber) {
        return restTemplate.postForObject(URL + "/deactivate/" + userNumber, null , Boolean.class);
    }

    @RequestMapping(path = "/enrolled/{subjectId}", method = RequestMethod.GET)
    public List<User> findAllUserIdBySubject(@PathVariable("subjectId") String subjectId) {
        List<Integer> enrolledUserId = restTemplate.getForObject(userSubjectURL + "/enrolled/" + subjectId, List.class);
        List<User> users = new ArrayList<>();
        enrolledUserId.forEach(id -> {
            users.add(restTemplate.getForObject(URL + "/userId/" + id, User.class));
        });
        return users;
    }
}
