package marks.marksmanagement.mark.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return markId.equals(mark.markId) &&
                grade.equals(mark.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(markId, grade);
    }
}