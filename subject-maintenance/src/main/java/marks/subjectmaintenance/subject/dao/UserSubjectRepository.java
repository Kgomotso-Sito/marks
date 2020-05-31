package marks.subjectmaintenance.subject.dao;

import marks.subjectmaintenance.subject.entity.UserSubject;
import marks.subjectmaintenance.subject.entity.UserSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubjectRepository extends JpaRepository<UserSubject, Integer> {
    UserSubject findUserSubjectsByUserSubjectId(UserSubjectId userSubjectId);
    List<UserSubject> findUserSubjectsByUserSubjectId_UserId(Integer userId);
    List<UserSubject> findUserSubjectsByUserSubjectId_SubjectId(Integer subjectId);

}
