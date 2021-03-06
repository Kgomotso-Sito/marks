package marks.subjectmaintenance.subject.service;

import marks.subjectmaintenance.subject.dao.UserSubjectRepository;
import marks.subjectmaintenance.subject.dto.UserSubjectList;
import marks.subjectmaintenance.subject.entity.UserSubject;
import marks.subjectmaintenance.subject.entity.UserSubjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSubjectService {

    @Autowired
    private UserSubjectRepository userSubjectRepository;

    public UserSubject createOrUpdate(UserSubjectId userSubjectId) {
        UserSubject userSubject = new UserSubject();
        userSubject.setUserSubjectId(userSubjectId);
        UserSubject newUserSubject = userSubjectRepository.saveAndFlush(userSubject);
        return newUserSubject;
    }

    public UserSubject createOrUpdate(UserSubject userSubject) {
        UserSubject newUserSubject = userSubjectRepository.saveAndFlush(userSubject);
        return newUserSubject;
    }

    public UserSubject findUserSubjectById(UserSubjectId userSubjectId) {
        UserSubject userSubject = userSubjectRepository.findUserSubjectsByUserSubjectId(userSubjectId);
        return userSubject;
    }

    public UserSubjectList findAllUserSubjectsByUserId(Integer userId) {
        return new UserSubjectList(userSubjectRepository.findUserSubjectsByUserSubjectId_UserId(userId));
    }

    public UserSubjectList findUserSubjectsByUserSubjectId(Integer subjectId) {
        return new UserSubjectList(userSubjectRepository.findUserSubjectsByUserSubjectId_SubjectId(subjectId));
    }

    public boolean deactivateUserSubject(UserSubjectId userSubjectId) {
        UserSubject userSubject = userSubjectRepository.findUserSubjectsByUserSubjectId(userSubjectId);
        if (userSubject == null) {
            return false;
        } else {
            userSubject.setRegistered(Boolean.FALSE);
            createOrUpdate(userSubject);
            return true;
        }
    }

    public UserSubjectList findAllUserSubjects() {
        return new UserSubjectList(userSubjectRepository.findAll());
    }

    public List<Integer> findAllUserIdBySubject(int subjectId){
        UserSubjectList userSubjectsList = findUserSubjectsByUserSubjectId(subjectId);
        List<Integer> enrolledUserIds = new ArrayList<>();

        userSubjectsList.getUserSubjects().forEach(userSubject -> {
            if(userSubject.getRegistered()) {
                enrolledUserIds.add(userSubject.getUserSubjectId().getUserId());
            }
        });
        return enrolledUserIds;
    }
}
