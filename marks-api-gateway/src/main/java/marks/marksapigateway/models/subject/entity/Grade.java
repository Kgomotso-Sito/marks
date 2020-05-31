package marks.marksapigateway.models.subject.entity;

import java.util.List;

public class Grade {
    private String subject;
    private List<Integer> grades;

    public Grade(String subject, List<Integer> grades) {
        this.subject = subject;
        this.grades = grades;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }
}
