package validCoding.bean;

/**
 * @author  王珺宇 131250044
 *
 *
 */
public class ProjectCode {

    private int project_id;                   //项目ID
    private String type;                      //文件类型(.java)或文件作用分类（IDE配置文件）
    private int comment_line;                 //总评论行数
    private int blank_line;                   //总空行数
    private int line_total;                   //总行数
    private int useful_line;                  //有用代码行数


    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getComment_line() {
        return comment_line;
    }

    public void setComment_line(int comment_line) {
        this.comment_line = comment_line;
    }

    public int getBlank_line() {
        return blank_line;
    }

    public void setBlank_line(int blank_line) {
        this.blank_line = blank_line;
    }

    public int getLine_total() {
        return line_total;
    }

    public void setLine_total(int line_total) {
        this.line_total = line_total;
    }

    public int getUseful_line() {
        return useful_line;
    }

    public void setUseful_line(int useful_line) {
        this.useful_line = useful_line;
    }
}
