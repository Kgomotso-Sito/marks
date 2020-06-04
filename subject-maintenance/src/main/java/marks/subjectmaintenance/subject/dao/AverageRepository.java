package marks.subjectmaintenance.subject.dao;

import marks.subjectmaintenance.subject.entity.Assessment;
import marks.subjectmaintenance.subject.entity.Average;
import marks.subjectmaintenance.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AverageRepository extends JpaRepository<Average, Integer> {
}
