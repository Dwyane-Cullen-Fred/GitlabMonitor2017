package org.bean;


public class Contribution {

    private String student;               //学生名字
    private int weight;                   //贡献权重


    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return student + " " + weight;
    }
}
