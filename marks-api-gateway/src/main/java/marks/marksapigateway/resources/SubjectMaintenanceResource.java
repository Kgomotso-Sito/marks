package marks.marksapigateway.resources;

import marks.marksapigateway.models.subject.dto.SubjectList;
import marks.marksapigateway.models.subject.entity.Subject;
import marks.marksapigateway.models.subject.entity.UserSubjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins="*", maxAge=3600)
public class SubjectMaintenanceResource {

    private String URL = "http://subject-maintenance/subjects";
    private String userSubjectURL = "http://subject-maintenance/userSubject";


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json")
    public boolean createSubject(@RequestBody Subject subject) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Subject> entity = new HttpEntity<>(subject, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(URL + "/create", entity, String.class);

        return (result.getStatusCodeValue() == 201 || result.getStatusCodeValue() == 200);
    }

    @RequestMapping(path = "/all", produces = "application/json")
    public List<Subject> findAll() {
        SubjectList subjectList = restTemplate.getForObject(URL + "/all", SubjectList.class);
        return subjectList.getSubjects();
    }

    @RequestMapping(path = "/{subjectId}", method = RequestMethod.GET)
    public Subject findSubjectById(@PathVariable("subjectId") String subjectId) {
        Subject subject = restTemplate.getForObject(URL + "/" + subjectId, Subject.class);
        return subject;
    }

    @RequestMapping(path = "/deactivate/{subjectId}")
    public boolean deactivateSubject(@PathVariable("subjectId") String subjectId) {
        return restTemplate.postForObject(URL + "/deactivate/" + subjectId, null, Boolean.class);
    }

    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public List<Subject> findSubjectsByUserId(@PathVariable("userId") String userId) {
        SubjectList subjectList = restTemplate.getForObject(URL + "/user/" + userId, SubjectList.class);
        return subjectList.getSubjects();
    }

    @RequestMapping(path = "/enroll", method = RequestMethod.POST, consumes = "application/json")
    public boolean createUserSubject(@RequestBody UserSubjectId userSubjectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserSubjectId> entity = new HttpEntity<>(userSubjectId, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(userSubjectURL + "/enroll", entity, String.class);

        return (result.getStatusCodeValue() == 201 || result.getStatusCodeValue() == 200);
    }

    @RequestMapping(path = "/deregister", method = RequestMethod.POST, consumes = "application/json")
    public boolean deregisterSubject(@RequestBody UserSubjectId userSubjectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserSubjectId> entity = new HttpEntity<>(userSubjectId, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(userSubjectURL + "/deregister", entity, String.class);

        return (result.getStatusCodeValue() == 201 || result.getStatusCodeValue() == 200);
    }
}
