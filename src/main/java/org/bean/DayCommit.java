package org.bean;

import java.time.LocalDate;

/**
 * 记录每天所有团队提交的总的情况
 * Created by XXH on 2017/3/29.
 */
public class DayCommit {

    private LocalDate day;                               //提交日期
    private int commit_count;                            //当天的提交量
    private int add_line;                                //当天的增加代码
    private int delete_line;                             //当天的删除行数
    private int java_file;                               //当天的文件数

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public int getCommit_count() {
        return commit_count;
    }

    public void setCommit_count(int commit_count) {
        this.commit_count = commit_count;
    }

    public int getAdd_line() {
        return add_line;
    }

    public void setAdd_line(int add_line) {
        this.add_line = add_line;
    }

    public int getDelete_line() {
        return delete_line;
    }

    public void setDelete_line(int delete_line) {
        this.delete_line = delete_line;
    }

    public int getJava_file() {
        return java_file;
    }

    public void setJava_file(int java_file) {
        this.java_file = java_file;
    }
}
