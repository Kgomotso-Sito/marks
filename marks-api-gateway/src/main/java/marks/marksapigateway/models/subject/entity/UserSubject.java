package marks.marksapigateway.models.subject.entity;

import java.util.Date;

public class UserSubject {

    private UserSubjectId userSubjectId;

    private Date date;

    private Boolean registered;

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
