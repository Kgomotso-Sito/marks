package marks.subjectmaintenance.subject.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AverageId implements Serializable {
    private Integer month;
    private Integer subjectId;

    public AverageId(){
        // default constructor
    }

    public AverageId(Integer month, Integer assessmentId) {
        this.month = month;
        this.subjectId = assessmentId;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }



}
