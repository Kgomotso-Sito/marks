package marks.subjectmaintenance.resources;

import marks.subjectmaintenance.subject.dto.AssessmentList;
import marks.subjectmaintenance.subject.entity.Assessment;
import marks.subjectmaintenance.subject.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/assessment")
public class AssessmentResource {

    @Autowired
    AssessmentService assessmentService;

    @RequestMapping("")
    public String home() {
        return "Assessment management micro-service is running";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean createAssessment(@RequestBody Assessment assessment){
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

}
