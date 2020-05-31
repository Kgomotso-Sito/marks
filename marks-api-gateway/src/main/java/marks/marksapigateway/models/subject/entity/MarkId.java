package marks.marksapigateway.models.subject.entity;

public class MarkId {
    private String userNumber;
    private Integer assessmentId;

    public MarkId(){
        // default constructor
    }

    public MarkId(String userNumber, Integer assessmentId) {
        this.userNumber = userNumber;
        this.assessmentId = assessmentId;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }
}
