package marks.marksmanagement.mark.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mark")
public  class Mark {
    @EmbeddedId
    private MarkId markId;

    private Integer grade;

    public Mark(MarkId markId) {
        this.markId = markId;
    }

    public Mark() {
    }

    public MarkId getMarkId() {
        return markId;
    }

    public void setMarkId(MarkId mark) {
        markId = mark;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}