package validCoding.dao;

import org.springframework.stereotype.Repository;
import validCoding.bean.DailyCommit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  王珺宇 131250044
 */
@Repository
public class DailyCommitDao extends BaseDaoImpl  {


    /**
     * @param project_id 项目id
     * @param iteration_id 迭代id
     * @param screenOut_list 筛去的代码类型
     * @return 某项目某个迭代，筛去某些代码类型以后提每日提交数量
     */
    public List<DailyCommit> getProjectDailyCommit(int project_id, int iteration_id, String[] screenOut_list) {
        Map<String, Object> map = new HashMap<>();
        String iteration = switchIteration(iteration_id);
        map.put("project_id", project_id);
        map.put("iteration", iteration);

        List<DailyCommit> projectDailyCommit_list;

        if(screenOut_list.length==0){ //没有筛除
            projectDailyCommit_list =
                    sqlSession.selectList("DailyCommit.selectProjectDailyCommitByIDAndIterationWithNoScreenOut", map);
        }
        else{
            map.put("screenOut_list", screenOut_list);
            projectDailyCommit_list =
                    sqlSession.selectList("DailyCommit.selectProjectDailyCommitByIDAndIterationAndScreenOut", map);
        }


        return projectDailyCommit_list;
    }



    /**
     * @param author 学生姓名
     * @param iteration_id 迭代id
     * @param screenOut_list 筛去的代码类型
     * @return 某学生某个迭代，筛去某些代码类型以后提每日提交数量
     */
    public List<DailyCommit> getStudentDailyCommit(String author, int iteration_id, String[] screenOut_list) {
        Map<String, Object> map = new HashMap<>();
        String iteration = switchIteration(iteration_id);
        map.put("author", author);
        map.put("iteration", iteration);

        List<DailyCommit> studentDailyCommit_list;

        if(screenOut_list.length==0){ //没有筛除
            studentDailyCommit_list =
                    sqlSession.selectList("DailyCommit.selectStudentDailyCommitByAuthorAndIterationWithNoScreenOut", map);
        }
        else{
            map.put("screenOut_list", screenOut_list);
            studentDailyCommit_list =
                    sqlSession.selectList("DailyCommit.selectStudentDailyCommitByAuthorAndIterationAndScreenOut", map);
        }


        return studentDailyCommit_list;
    }









    private String switchIteration(int iteration_id){
        String iteration = "迭代一";
        switch(iteration_id){
            case 1:
                iteration = "迭代一"; break;
            case 2:
                iteration = "迭代二"; break;
            case 3:
                iteration = "迭代三"; break;
        }
        return iteration;
    }


}
