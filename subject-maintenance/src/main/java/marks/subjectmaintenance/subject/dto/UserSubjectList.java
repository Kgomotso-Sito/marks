package marks.subjectmaintenance.subject.dto;

import marks.subjectmaintenance.subject.entity.UserSubject;

import java.util.List;

public class UserSubjectList {
    private List<UserSubject> userSubjects;

    public UserSubjectList(List<UserSubject> userSubjects) {
        this.userSubjects = userSubjects;
    }

    public List<UserSubject> getUserSubjects() {
        return userSubjects;
    }
    public void setUserSubjects(List<UserSubject> userSubjects) {
        this.userSubjects = userSubjects;
    }
}
