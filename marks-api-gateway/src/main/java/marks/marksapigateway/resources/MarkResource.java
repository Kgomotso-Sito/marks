package marks.marksapigateway.resources;

import marks.marksapigateway.models.subject.dto.MarkList;
import marks.marksapigateway.models.subject.entity.Mark;
import marks.marksapigateway.models.subject.entity.MarkId;
import marks.marksapigateway.models.subject.entity.Subject;
import marks.marksapigateway.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/marks")
@CrossOrigin(origins="*", maxAge=3600)
public class MarkResource {

    private String URL = "http://marks-1591161650651/marks";
    private String usersURL = "http://user-management-1591161650651/users";

    private String userSubjectURL = "http://subject-1591161650651/usersubject";
    private String subjectURL = "http://subject-1591161650651/subjects";


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json")
    public boolean createMark(@RequestBody Mark mark){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Mark> entity = new HttpEntity<>(mark, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(URL + "/create", entity, String.class);

        return (result.getStatusCodeValue() == 201 || result.getStatusCodeValue() == 200);
    }

    @RequestMapping(path = "/assessment/{assessmentId}", method = RequestMethod.GET)
    public List<Mark> findAllMarks(@PathVariable("assessmentId") Integer assessmentId) {
        return findAllMarksByAssessment(assessmentId);
    }

    public List<Mark> findAllMarksByAssessment(Integer assessmentId) {
        //Get assessment to get subject
        Subject subject = restTemplate.getForObject(subjectURL + "/assessment/" + assessmentId, Subject.class);

        //Get all marks
        MarkList marks = restTemplate.getForObject(URL + "/assessment/" + assessmentId, MarkList.class);

        //Get all enrolled users
        List<Integer> enrolledUserId = restTemplate.getForObject(userSubjectURL + "/enrolled/" + subject.getId(), List.class);

        //Now get the matching user numbers
        List<String> users = new ArrayList<>();
        enrolledUserId.forEach(id -> {
            users.add(restTemplate.getForObject(usersURL + "/userId/" + id, User.class).getUserNumber());
        });

        //Add marks for users that are not marked yet
        users.forEach(user ->{
            if(!marks.getMarks().stream().anyMatch(mark -> mark.getMarkId().getUserNumber().equals(user))) {
                marks.getMarks().add(new Mark(new MarkId(user, assessmentId)));
            }
        });

        return marks.getMarks();
    }
}
