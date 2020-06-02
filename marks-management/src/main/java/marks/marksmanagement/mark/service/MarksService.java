package marks.marksmanagement.mark.service;

import marks.marksmanagement.mark.dao.MarksRepository;
import marks.marksmanagement.mark.dto.MarkList;
import marks.marksmanagement.mark.entity.Mark;
import marks.marksmanagement.mark.entity.MarkId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;

    public Mark createOrUpdate(Mark mark) {
        Mark newMark = marksRepository.saveAndFlush(mark);
        return newMark;
    }

    public Mark findMarkByMarkId(MarkId markId) {
        Mark mark = marksRepository.findMarkByMarkId(markId);
        return mark;
    }

    public MarkList findMarksByMarkId_AssessmentId(Integer assessment_Id) {
        return new MarkList(marksRepository.findMarksByMarkId_AssessmentId(assessment_Id));
    }
}
