package org.bean;

import java.time.LocalDate;

/**
 * 每个迭代项目的提交情况
 * Created by XXH on 2017/4/1.
 */
public class IterationProjectCommit {
    private int id;                                     //项目id
    private String name;                                //项目名称
    private LocalDate day;                              //提交日期
    private int total_commit;                           //迭代提交总量
    private int total_add;                              //迭代增加代码行数
    private int total_delete;                           //迭代删除代码行数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
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
