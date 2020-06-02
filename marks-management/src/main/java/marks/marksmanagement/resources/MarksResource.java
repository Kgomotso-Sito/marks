package marks.marksmanagement.resources;

import marks.marksmanagement.mark.dto.MarkList;
import marks.marksmanagement.mark.entity.Mark;
import marks.marksmanagement.mark.entity.MarkId;
import marks.marksmanagement.mark.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarksResource {

    @Autowired
    MarksService marksService;

    @RequestMapping("")
    public String home() {
        return "Marks management micro-service is running";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean createMark(@RequestBody Mark mark){
        return marksService.createOrUpdate(mark) != null;
    }

    @RequestMapping(path = "/assessment/{assessmentId}", method = RequestMethod.GET)
    public MarkList getMarksForAssessment(@PathVariable("assessmentId") Integer assessmentId){
        MarkList markList = marksService.findMarksByMarkId_AssessmentId(assessmentId);
        return markList;
    }

}
