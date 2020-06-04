package marks.subjectmaintenance.subject.dto;

import marks.subjectmaintenance.subject.entity.Assessment;
import marks.subjectmaintenance.subject.entity.Subject;

import java.util.List;

public class AssessmentList {
    private List<Assessment> assessments;

    public AssessmentList(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }
    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
