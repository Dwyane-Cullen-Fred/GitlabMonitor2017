package validCoding.bean;

import java.sql.Date;


/**
 * @author  王珺宇 131250044
 */
public class DailyCommit {
    private Date commit_day;   //提交日期
    private int day_add;       //当日添加行数
    private int day_delete;    //当日删除行数
    private int day_total;     //当日提交总行数






    public Date getCommit_day() {
        return commit_day;
    }

    public void setCommit_day(Date commit_day) {
        this.commit_day = commit_day;
    }

    public int getDay_total() {
        return day_total;
    }

    public void setDay_total(int day_total) {
        this.day_total = day_total;
    }

}
