package org.bean;

/**
 * Created by XXH on 2017/4/24.
 */
public class AggregatedProjectCode {

    private int project_id;                   //项目ID
    private String type;                      //文件类型
    private int add_line_total;               //总增加行数
    private int delete_line_total;            //总删减行数
    private int comment_line_total;           //总评论行数
    private int blank_line_total;             //总空行数
    private int line_total;                   //总行数
    private int valid_code_line;              //有效代码行数



    public int getProject_id() {return project_id;}

    public String getType() {return type;}

    public int getAdd_line_total() {return add_line_total;}

    public int getDelete_line_total() {return delete_line_total;}

    public int getComment_line_total() {return comment_line_total;}

    public int getBlank_line_total() {return blank_line_total;}

    public int getLine_total() {return line_total;}

    public int getValid_code_line() {return valid_code_line;}




    public void setProject_id(int project_id) {this.project_id = project_id;}

    public void setType(String type) {this.type = type;}

    public void setAdd_line_total(int add_line_total) {this.add_line_total = add_line_total;}

    public void setDelete_line_total(int delete_line_total) {this.delete_line_total = delete_line_total;}

    public void setComment_line_total(int comment_line_total) {this.comment_line_total = comment_line_total;}

    public void setBlank_line_total(int blank_line_total) {this.blank_line_total = blank_line_total;}

    public void setLine_total(int line_total) {this.line_total = line_total;}

    public void setValid_code_line(int valid_code_line) {this.valid_code_line = valid_code_line;}


}
