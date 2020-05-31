package marks.marksapigateway.models.subject.entity;

import java.util.List;

public class Grade {
    private String label;
    private List<Integer> data;

    public Grade(String label, List<Integer> data) {
        this.label = label;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> grades) {
        this.data = grades;
    }
}
