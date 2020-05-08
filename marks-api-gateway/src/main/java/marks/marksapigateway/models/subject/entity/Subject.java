package marks.marksapigateway.models.subject.entity;

import java.util.List;


public class Subject {

    private int id;

    private String code;
    private String description;
    private Boolean active = Boolean.TRUE;

    private List<Assessment> assessments;

    public Subject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Assessment> getAssessmentList() {
        return assessments;
    }

    public void setAssessmentList(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}