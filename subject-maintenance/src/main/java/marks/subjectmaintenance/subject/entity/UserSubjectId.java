package marks.subjectmaintenance.subject.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserSubjectId implements Serializable {
    private Integer userId;
    private Integer subjectId;

    public UserSubjectId(){
        // default constructor
    }

    public UserSubjectId(Integer userId, Integer subjectId) {
        this.userId = userId;
        this.subjectId = subjectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
