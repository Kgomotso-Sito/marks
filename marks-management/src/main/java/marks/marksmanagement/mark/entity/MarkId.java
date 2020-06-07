package marks.marksmanagement.mark.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkId markId = (MarkId) o;
        return userNumber.equals(markId.userNumber) &&
                assessmentId.equals(markId.assessmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNumber, assessmentId);
    }
}
