package marks.subjectmaintenance.subject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Average")
public class Average {
    @EmbeddedId
    private AverageId averageId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Subject averageSubject;

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


    public Subject getAverageSubject() {
        return averageSubject;
    }

    public void setAverageSubject(Subject averageSubject) {
        this.averageSubject = averageSubject;
    }
}
