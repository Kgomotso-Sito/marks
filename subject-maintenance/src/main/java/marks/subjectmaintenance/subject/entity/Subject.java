package marks.subjectmaintenance.subject.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String code;
    private String description;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active = Boolean.TRUE;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Assessment> assessmentList;

    @OneToMany(mappedBy = "averageSubject", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Average> averages;

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
        return assessmentList;
    }

    public void setAssessmentList(List<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
    }

    public List<Average> getAverages() {
        return averages;
    }

    public void setAverages(List<Average> averages) {
        this.averages = averages;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}