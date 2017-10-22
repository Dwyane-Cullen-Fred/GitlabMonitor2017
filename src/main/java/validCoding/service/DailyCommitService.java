package validCoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validCoding.bean.DailyCommit;
import validCoding.dao.DailyCommitDao;

import java.util.List;

/**
 * Created by Dwyane Cullen Fred on 2017/5/14.
 */

@Service
public class DailyCommitService {

    @Autowired
    private DailyCommitDao dailyCommitDao;


    /**
     * @param project_id 项目id
     * @param iteration_id 迭代id
     * @param screenOut_list 筛去的代码类型
     * @return 某项目某个迭代，筛去某些代码类型以后提每日提交数量
     */
    public List<DailyCommit> getProjectDailyCommit(int project_id, int iteration_id, String[] screenOut_list) {
        return dailyCommitDao.getProjectDailyCommit(project_id, iteration_id, screenOut_list);
    }



    /**
     * @param author 学生姓名
     * @param iteration_id 迭代id
     * @param screenOut_list 筛去的代码类型
     * @return 某学生某个迭代，筛去某些代码类型以后提每日提交数量
     */
    public List<DailyCommit> getStudentDailyCommit(String author, int iteration_id, String[] screenOut_list) {
        return dailyCommitDao.getStudentDailyCommit(author, iteration_id, screenOut_list);
    }



}
