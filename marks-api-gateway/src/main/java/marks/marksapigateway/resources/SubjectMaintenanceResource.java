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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins="*", maxAge=3600)
public class SubjectMaintenanceResource {

    private String URL = "http://subject-1591161650651";


    private String subjectURL = URL + "/subjects";
    private String userSubjectURL = URL + "/usersubject";
    private String assessmentURL= URL + "/assessment";
    private String marksURL = "http://marks-1591161650651/marks";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json")
    public boolean createSubject(@RequestBody Subject subject) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Subject> entity = new HttpEntity<>(subject, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(subjectURL + "/create", entity, String.class);

        return (result.getStatusCodeValue() == 201 || result.getStatusCodeValue() == 200);
    }

    @RequestMapping(path = "/all", produces = "application/json")
    public List<Subject> findAll() {
        SubjectList subjectList = restTemplate.getForObject(subjectURL + "/all", SubjectList.class);
        return subjectList.getSubjects();
    }

    @RequestMapping(path = "/{subjectId}", method = RequestMethod.GET)
    public Subject findSubjectById(@PathVariable("subjectId") String subjectId) {
        Subject subject = restTemplate.getForObject(subjectURL + "/" + subjectId, Subject.class);
        return subject;
    }

    @RequestMapping(path = "/deactivate/{subjectId}")
    public boolean deactivateSubject(@PathVariable("subjectId") String subjectId) {
        return restTemplate.postForObject(subjectURL + "/deactivate/" + subjectId, null, Boolean.class);
    }

    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public List<Subject> findSubjectsByUserId(@PathVariable("userId") String userId) {
        SubjectList subjectList = restTemplate.getForObject(subjectURL + "/user/" + userId, SubjectList.class);
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
    public List<Grade> getAllAverageMarks() {

        SubjectList subjectList = restTemplate.getForObject(subjectURL + "/all", SubjectList.class);
        List<Grade> grades = new ArrayList<>();

        subjectList.getSubjects().forEach(subject -> {
            List<Integer> average = new ArrayList<>(12);
            if(subject.getAverages() != null) {
                subject.getAverages().forEach(average1 -> {
                    average.add(average1.getAverageId().getMonth(), average1.getValue());
                });
            } else {
                average.addAll(Collections.nCopies(12, 0));
            }
            Grade grade = new Grade(subject.getDescription(),average);
            grades.add(grade);
        });

        return grades;
    }

    @RequestMapping(path = "/average/{subjectId}", method = RequestMethod.GET)
    public List<Average> updateAverage(@PathVariable("subjectId") String subjectId) {
        Subject subject = restTemplate.getForObject(subjectURL + "/" + subjectId, Subject.class);
        subject.setAverages(getMonthlyMarks(subject));
        createSubject(subject);
        return subject.getAverages();
    }


    public List<Average> getMonthlyMarks(Subject subject){
        AssessmentList assessmentList = restTemplate.getForObject(assessmentURL + "/subject/" + subject.getId(), AssessmentList.class);
        List<Average> list = new ArrayList<>();

        for (int i= 0; i < 12 ;i++) {
            List<Assessment> monthlyAssessemnts = getAssessmentForMonth(assessmentList.getAssessments(),i);
            Average average = new Average();
            AverageId averageId = new AverageId(i, subject.getId());
            average.setAverageId(averageId);
            average.setValue(getAverageMark(monthlyAssessemnts));

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
