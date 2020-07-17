package marks.marksapigateway.resources;

import marks.marksapigateway.models.subject.dto.AssessmentList;
import marks.marksapigateway.models.subject.entity.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/assessment")
@CrossOrigin(origins="*", maxAge=3600)
public class AssessmentMaintenanceResource {

    private String URL= "https://subject-1591161650651.herokuapp.com/assessment";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json")
    public boolean createUser(@RequestBody Assessment assessment){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Assessment> entity = new HttpEntity<>(assessment, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(URL + "/create/" + assessment.getSubjectId(), entity, String.class);

        return (result.getStatusCodeValue() == 201 || result.getStatusCodeValue() == 200);
    }

    @RequestMapping(path = "/all", produces = "application/json")
    public List<Assessment> findAll() {
        AssessmentList assessmentList = restTemplate.getForObject(URL + "/all", AssessmentList.class);
        return assessmentList.getAssessments();
    }

    @RequestMapping(path = "/{assessmentId}", method = RequestMethod.GET)
    public Assessment findSubjectById(@PathVariable("assessmentId") String assessmentId) {
        Assessment assessment = restTemplate.getForObject(URL + "/" + assessmentId, Assessment.class);
        return assessment;
    }

    @RequestMapping(path = "/deactivate/{subjectId}")
    public boolean deactivateSubject(@PathVariable("subjectId") String subjectId) {
        return restTemplate.postForObject(URL + "/deactivate/" + subjectId, null , Boolean.class);
    }

    @RequestMapping(path = "/subject/{subjectId}", method = RequestMethod.GET)
    public List<Assessment> getAllAssessmentsById(@PathVariable("subjectId") String subjectId) {
        AssessmentList assessmentList = restTemplate.getForObject(URL + "/subject/" + subjectId, AssessmentList.class);
        return assessmentList.getAssessments();
    }
}
