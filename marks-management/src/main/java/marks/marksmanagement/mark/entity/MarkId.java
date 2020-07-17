package marks.marksmanagement.mark.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MarkId implements Serializable {
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
