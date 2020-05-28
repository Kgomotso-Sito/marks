package marks.subjectmaintenance.resources;

import marks.subjectmaintenance.subject.dto.SubjectList;
import marks.subjectmaintenance.subject.entity.Subject;
import marks.subjectmaintenance.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectResource {

    @Autowired
    SubjectService subjectService;

    @RequestMapping("")
    public String home() {
        return "Subject management micro-service is running";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean createSubject(@RequestBody Subject subject){
        return subjectService.createOrUpdate(subject) != null;
    }

    @RequestMapping("/{subjectId}")
    public Subject getSubjectById(@PathVariable("subjectId") int subjectId) {
        return subjectService.findBySubjectById(subjectId);
    }

    @RequestMapping("/all")
    public SubjectList getAllSubjects(){
        return subjectService.findAllSubjects();
    }

    @RequestMapping("/active")
    public SubjectList getAllActiveSubjects(){
        return subjectService.findAllActiveSubjects();
    }

    @RequestMapping(path = "/deactivate/{subjectId}", method = RequestMethod.POST)
    public boolean deactivateSubject(@PathVariable("subjectId") int subjectId) {
        return subjectService.deactivateSubject(subjectId);
    }

    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public SubjectList getAllSubjectsByUser(@PathVariable("userId") int userId){
        return subjectService.findAllSubjectsByUser(userId);
    }
}
