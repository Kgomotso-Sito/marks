package marks.marksapigateway.models.subject.dto;

import marks.marksapigateway.models.subject.entity.Subject;
import java.util.List;

public class SubjectList {
    private List<Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}

