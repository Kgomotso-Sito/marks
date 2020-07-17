package marks.marksapigateway.models.subject.entity;


public class Average {
    private AverageId averageId;
    private Subject subject;

    private int value;

    public Average() {
    }

    public AverageId getAverageId() {
        return averageId;
    }

    public void setAverageId(AverageId averageId) {
        this.averageId = averageId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
