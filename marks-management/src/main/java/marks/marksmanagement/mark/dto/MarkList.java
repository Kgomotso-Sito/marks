package marks.marksmanagement.mark.dto;


import marks.marksmanagement.mark.entity.Mark;

import java.util.List;

public class MarkList {
    private List<Mark> marks;

    public MarkList(List<Mark> marks) {
        this.marks = marks;
    }

    public List<Mark> getMarks() {
        return marks;
    }
    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}
