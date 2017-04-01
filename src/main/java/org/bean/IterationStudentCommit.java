package org.bean;

/**
 * 记录每个迭代学生的提交情况
 * Created by XXH on 2017/4/1.
 */
public class IterationStudentCommit {
    private int id;                                 //项目id
    private String student;                         //学生编号
    private int total_commit;                       //迭代总提交量
    private int total_add;                          //迭代增加代码行数
    private int total_delete;                       //迭代删除代码行数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getTotal_commit() {
        return total_commit;
    }

    public void setTotal_commit(int total_commit) {
        this.total_commit = total_commit;
    }

    public int getTotal_add() {
        return total_add;
    }

    public void setTotal_add(int total_add) {
        this.total_add = total_add;
    }

    public int getTotal_delete() {
        return total_delete;
    }

    public void setTotal_delete(int total_delete) {
        this.total_delete = total_delete;
    }
}
