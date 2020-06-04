package marks.subjectmaintenance.resources;

import marks.subjectmaintenance.subject.dto.UserSubjectList;
import marks.subjectmaintenance.subject.entity.UserSubjectId;
import marks.subjectmaintenance.subject.service.UserSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usersubject")
public class UserSubjectResource {

    @Autowired
    UserSubjectService userSubjectService;

    @RequestMapping("")
    public String home() {
        return "User subject management micro-service is running";
    }

    @RequestMapping(path = "/enroll", method = RequestMethod.POST)
    public boolean createUserSubject(@RequestBody UserSubjectId userSubjectId){
        return userSubjectService.createOrUpdate(userSubjectId) != null;
    }

    @RequestMapping("/all")
    public UserSubjectList getAllUserSubjects(){
        return userSubjectService.findAllUserSubjects();
    }

    @RequestMapping("/all/{userId}")
    public UserSubjectList getAllUserSubjects(@PathVariable("userId") Integer userId){
        return userSubjectService.findAllUserSubjectsByUserId(userId);
    }

    @RequestMapping("/subject/{subjectId}")
    public UserSubjectList getAllUserSubjectsBySubjectId(@PathVariable("subjecId") Integer subjectId){
        return userSubjectService.findUserSubjectsByUserSubjectId(subjectId);
    }

    @RequestMapping(path = "/deregister", method = RequestMethod.POST)
    public boolean deregisterSubject(@RequestBody UserSubjectId userSubjectId){
        return userSubjectService.deactivateUserSubject(userSubjectId);
    }

    @RequestMapping(path = "/enrolled/{subjectId}", method = RequestMethod.GET)
    public List<Integer> findAllUserIdBySubject(@PathVariable("subjectId") int subjectId){
        return userSubjectService.findAllUserIdBySubject(subjectId);
    }
}
