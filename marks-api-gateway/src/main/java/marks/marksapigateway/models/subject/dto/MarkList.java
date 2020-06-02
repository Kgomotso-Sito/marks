package marks.marksapigateway.models.subject.dto;

import marks.marksapigateway.models.subject.entity.Mark;
import java.util.List;

public class MarkList {
    private List<Mark> marks;

    public List<Mark> getMarks() {
        return marks;
    }
    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}
