package org.bean;

import java.io.Serializable;

public class ValidCommit implements Serializable{

    private String member;                       //组员名字
    private int total_add;                       //总的增加行数
    private int total_delete;                    //总的减少行数
    private int total_file;                      //总的文件数目
    private int valid_add;                       //有效的增加行数
    private int valid_delete;                    //有效的减少行数
    private int valid_file;                      //有效的文件

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
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

    public int getTotal_file() {
        return total_file;
    }

    public void setTotal_file(int total_file) {
        this.total_file = total_file;
    }

    public int getValid_add() {
        return valid_add;
    }

    public void setValid_add(int valid_add) {
        this.valid_add = valid_add;
    }

    public int getValid_delete() {
        return valid_delete;
    }

    public void setValid_delete(int valid_delete) {
        this.valid_delete = valid_delete;
    }

    public int getValid_file() {
        return valid_file;
    }

    public void setValid_file(int valid_file) {
        this.valid_file = valid_file;
    }
}
