package marks.marksapigateway.models.subject.dto;

import marks.marksapigateway.models.subject.entity.Assessment;
import java.util.List;

public class AssessmentList {
    private List<Assessment> assessments;

    public List<Assessment> getAssessments() {
        return assessments;
    }
    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
