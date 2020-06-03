package marks.marksapigateway.models.subject.entity;

public  class Mark {
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