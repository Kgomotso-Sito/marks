package marks.subjectmaintenance.resources;

import marks.subjectmaintenance.subject.dto.AssessmentList;
import marks.subjectmaintenance.subject.entity.Assessment;
import marks.subjectmaintenance.subject.entity.Subject;
import marks.subjectmaintenance.subject.service.AssessmentService;
import marks.subjectmaintenance.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/assessment")
public class AssessmentResource {

    @Autowired
    AssessmentService assessmentService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping("")
    public String home() {
        return "Assessment management micro-service is running";
    }

    @RequestMapping(path = "/create/{subjectId}", method = RequestMethod.POST)
    public boolean createAssessment(@PathVariable("subjectId")  int subjectId,@RequestBody Assessment assessment){
        assessment.setSubject(subjectService.findBySubjectById(subjectId));

        return assessmentService.createOrUpdate(assessment) != null;
    }

    @RequestMapping("/{assessmentId}")
    public Assessment getAssessmentByAssessmentNumber(@PathVariable("assessmentId") int assessmentId) {
        return assessmentService.findAssessmentById(assessmentId);
    }

    @RequestMapping("/all")
    public AssessmentList getAllAssessmentsById(){
        return assessmentService.findAllAssessment();
    }

    @RequestMapping("/active")
    public AssessmentList getAllActiveAssessments(){
        return assessmentService.findAllActiveAssessment();
    }

    @RequestMapping(path = "/deactivate/{assessmentId}", method = RequestMethod.POST)
    public boolean deactivateAssessment(@PathVariable("assessmentId") int assessmentId) {
        return assessmentService.deactivateAssessment(assessmentId);
    }

    @RequestMapping("/subject/{subjectId}")
    public AssessmentList getAllAssessmentsSubjectId(@PathVariable("subjectId") int subjectId){
        Subject subject = subjectService.findBySubjectById(subjectId);
        return assessmentService.findAllAssessmentBySubject(subject);
    }

}
