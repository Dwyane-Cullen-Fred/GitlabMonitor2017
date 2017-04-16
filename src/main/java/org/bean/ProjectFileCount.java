package org.bean;

public class ProjectFileCount {

    private String student;
    private int line_count;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getLine_count() {
        return line_count;
    }

    public void setLine_count(int line_count) {
        this.line_count = line_count;
    }

    public ProjectFileCount(String student, int line_count){
        this.student = student;
        this.line_count = line_count;
    }
}
