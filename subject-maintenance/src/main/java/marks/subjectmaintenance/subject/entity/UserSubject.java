package marks.subjectmaintenance.subject.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UserSubject")
public class UserSubject {
    @EmbeddedId
    private UserSubjectId userSubjectId;

    private Date date = new Date();

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean registered = Boolean.TRUE;

    public UserSubject() {
    }

    public UserSubjectId getUserSubjectId() {
        return userSubjectId;
    }

    public void setUserSubjectId(UserSubjectId userSubjectId) {
        this.userSubjectId = userSubjectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }
}
