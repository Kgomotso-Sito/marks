package marks.marksmanagement.mark.dao;

import marks.marksmanagement.mark.entity.Mark;
import marks.marksmanagement.mark.entity.MarkId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarksRepository extends JpaRepository<Mark, Integer> {
    Mark findMarkByMarkId(MarkId markId);
    List<Mark> findMarksByMarkId_AssessmentId(Integer assessmentId);
}
