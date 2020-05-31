package marks.marksapigateway.resources;

import marks.marksapigateway.models.subject.dto.AssessmentList;
import marks.marksapigateway.models.subject.dto.MarkList;
import marks.marksapigateway.models.subject.dto.SubjectList;
import marks.marksapigateway.models.subject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins="*", maxAge=3600)
public class SubjectMaintenanceResource {

    private String URL = "http://subject-maintenance/subjects";
    private String userSubjectURL = "http://subject-maintenance/usersubject";
    private String assessmentURL= "http://subject-maintenance/assessment";
    private String marksURL = "http://marks-management-service/marks";


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

    @RequestMapping(path = "/enrolled/{subjectId}", method = RequestMethod.GET)
    public List<Integer> findAllUserIdBySubject(@PathVariable("subjectId") String subjectId) {
        List<Integer> enrolledUserId = restTemplate.getForObject(userSubjectURL + "/enrolled/" + subjectId, List.class);
        return enrolledUserId;
    }

    @RequestMapping(path = "/marks", method = RequestMethod.GET)
    public List<Grade> findAllUserIdBySubject() {
        SubjectList subjectList = restTemplate.getForObject(URL + "/all", SubjectList.class);
        List<Grade> grades = new ArrayList<>();


        subjectList.getSubjects().forEach(subject -> {
            grades.add(new Grade(subject.getDescription(), getMonthlyMarks(subject)));
        });

        return grades;
    }


    public List<Integer> getMonthlyMarks(Subject subject){
        AssessmentList assessmentList = restTemplate.getForObject(assessmentURL + "/subject/" + subject.getId(), AssessmentList.class);
        List<Integer> list = new ArrayList<>();

        for (int i= 0; i< 12 ;i++) {
            List<Assessment> monthlyAssessemnts = getAssessmentForMonth(assessmentList.getAssessments(),i);
            Integer average = getAverageMark(monthlyAssessemnts);
            list.add(average);
        }
        return list;
    }

    public List<Assessment> getAssessmentForMonth(List<Assessment> assessments, Integer month){
        return assessments.stream().filter(
                assessment -> assessment.getDate().getMonth() < month).collect(Collectors.toList());
    }


    public Integer getAverageMark(List<Assessment> assessments){
        List<Mark> allMarks = new ArrayList<>();
        List<Integer> grades = new ArrayList<>();

        assessments.forEach(assessment -> {
            List<Mark> marks = findAllMarksByAssessment(assessment.getId());
                allMarks.addAll(marks != null? marks : new ArrayList<>());
            }
        );

        allMarks.forEach(mark -> {
                grades.add(mark.getGrade());
            }
        );

        Integer sum = grades.stream().mapToInt(Integer::intValue).sum();

        return grades.size() > 0? sum/grades.size(): 0;
    }

    public List<Mark> findAllMarksByAssessment(Integer assessmentId) {
        MarkList marks = restTemplate.getForObject(marksURL + "/assessment/" + assessmentId, MarkList.class);
        return marks.getMarks();
    }

}
