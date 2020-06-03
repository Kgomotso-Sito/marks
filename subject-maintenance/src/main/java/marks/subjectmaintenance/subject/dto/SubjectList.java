package marks.subjectmaintenance.subject.dto;

import marks.subjectmaintenance.subject.entity.Subject;
import java.util.List;

public class SubjectList {
    private List<Subject> subjects;

    public SubjectList(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}

