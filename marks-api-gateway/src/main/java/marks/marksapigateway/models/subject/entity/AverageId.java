package marks.marksapigateway.models.subject.entity;

public class AverageId {
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
