package marks.subjectmaintenance.subject.service;

import marks.subjectmaintenance.subject.dao.AverageRepository;
import marks.subjectmaintenance.subject.dao.SubjectRepository;
import marks.subjectmaintenance.subject.dto.SubjectList;
import marks.subjectmaintenance.subject.dto.UserSubjectList;
import marks.subjectmaintenance.subject.entity.Assessment;
import marks.subjectmaintenance.subject.entity.Average;
import marks.subjectmaintenance.subject.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private AverageRepository averageRepository;

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private UserSubjectService userSubjectService;

    public Subject findBySubjectById(int subjectId)  {
        Subject subject = subjectRepository.findSubjectById(subjectId);
        return subject;
    }

    public Subject findBySubjectByAssessment(Assessment assessment)  {
        Subject subject = subjectRepository.findSubjectByAssessmentListContains(assessment);
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
        if(newSubject.getAverages() != null){
            newSubject.getAverages().forEach(average -> {
                average.setAverageSubject(newSubject);
                averageRepository.saveAndFlush(average);
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

    public SubjectList findAllSubjectsByUser(int userId){
        UserSubjectList userSubjectsList = userSubjectService.findAllUserSubjectsByUserId(userId);
        List<Subject> userSubjects = new ArrayList<>();

        userSubjectsList.getUserSubjects().forEach(userSubject -> {
            if(userSubject.getRegistered()) {
                userSubjects.add(subjectRepository.findSubjectById(userSubject.getUserSubjectId().getSubjectId()));
            }
        });

        return new SubjectList(userSubjects);
    }

}
