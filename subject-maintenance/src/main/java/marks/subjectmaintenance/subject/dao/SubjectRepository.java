package marks.subjectmaintenance.subject.dao;

import marks.subjectmaintenance.subject.entity.Assessment;
import marks.subjectmaintenance.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findSubjectById(int subjectId);
    List<Subject> findAllByActive(Boolean active);
    Subject findSubjectByAssessmentListContains(Assessment assessment);
}
