package marks.subjectmaintenance.subject.service;

import marks.subjectmaintenance.subject.dao.SubjectRepository;
import marks.subjectmaintenance.subject.dto.SubjectList;
import marks.subjectmaintenance.subject.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private AssessmentService assessmentService;

    public Subject findBySubjectById(int subjectId)  {
        Subject subject = subjectRepository.findSubjectById(subjectId);
        return subject;
    }

    public Subject createOrUpdate(Subject subject)  {
        Subject newSubject = subjectRepository.saveAndFlush(subject);
        if(newSubject.getAssessmentList() != null){
            newSubject.getAssessmentList().forEach(assessment -> {
                assessment.setSubject(newSubject);
                assessmentService.createOrUpdate(assessment);
            });
        }
        return newSubject;
    }

    public boolean deactivateSubject(int subjectId) {
        Subject subject = findBySubjectById(subjectId);
        if(subject == null) {
           return false;
        } else {
            subject.setActive(Boolean.FALSE);
            createOrUpdate(subject);
            return true;
        }
    }

    public SubjectList findAllSubjects(){ return new SubjectList(subjectRepository.findAll());
    }

    public SubjectList findAllActiveSubjects(){ return new SubjectList(subjectRepository.findAllByActive(Boolean.TRUE));
    }

}
