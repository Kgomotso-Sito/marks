package marks.subjectmaintenance.subject.service;

import marks.subjectmaintenance.subject.dao.AssessmentRepository;
import marks.subjectmaintenance.subject.dao.SubjectRepository;
import marks.subjectmaintenance.subject.dto.AssessmentList;
import marks.subjectmaintenance.subject.dto.SubjectList;
import marks.subjectmaintenance.subject.entity.Assessment;
import marks.subjectmaintenance.subject.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    public Assessment createOrUpdate(Assessment assessment) {
        Assessment newAssessment = assessmentRepository.saveAndFlush(assessment);
        return newAssessment;
    }

    public Assessment findAssessmentById(int assessmentId) {
        Assessment newAssessment = assessmentRepository.findAssessmentById(assessmentId);
        return newAssessment;
    }

    public boolean deactivateAssessment(int assessmentId) {
        Assessment assessment = assessmentRepository.findAssessmentById(assessmentId);
        if (assessment == null) {
            return false;
        } else {
            assessment.setActive(Boolean.FALSE);
            createOrUpdate(assessment);
            return true;
        }
    }

    public AssessmentList findAllAssessment() {
        return new AssessmentList(assessmentRepository.findAll());
    }

    public AssessmentList findAllActiveAssessment() {
        return new AssessmentList(assessmentRepository.findAllByActive(Boolean.TRUE));
    }

    public AssessmentList findAllAssessmentBySubject(Subject subject) {
        return new AssessmentList(assessmentRepository.findAllBySubject(subject));
    }
}
