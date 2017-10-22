package validCoding.dao;

import org.springframework.stereotype.Repository;
import validCoding.bean.ProjectCode;
import validCoding.bean.ProjectContribution;
import validCoding.bean.StudentCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author   王珺宇 131250044
 */
@Repository
public class ValidCodeAggregationDao extends BaseDaoImpl  {

    /**
     * @param project_id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同作用文件的行数
     */
    public List<ProjectCode> getProjectValidByIDAndIteration(int project_id, int iteration_id) {
        Map<String, Object> map = new HashMap<>();
        String table_name = switchTable(iteration_id);
        map.put("project_id", project_id);
        map.put("table_name", table_name);

        List<ProjectCode> projectValid_list =
                sqlSession.selectList("ValidCodeAggregation.selectProjectValidByIDAndIteration", map);

        return projectValid_list;
    }

    /**
     * @param project_id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同类型代码的行数
     */
    public List<ProjectCode> getProjectCompositionByIDAndIteration(int project_id, int iteration_id) {
        Map<String, Object> map = new HashMap<>();
        String table_name = switchTable(iteration_id);
        map.put("project_id", project_id);
        map.put("table_name", table_name);

        List<ProjectCode> projectComposition_list =
                sqlSession.selectList("ValidCodeAggregation.selectProjectCompositionByIDAndIteration", map);
        return projectComposition_list;
    }



    /**
     * @param project_id : 项目id
     * @param iteration_id ： 迭代
     * @return ： 某迭代，不同学生对该项目的贡献
     */
    public List<ProjectContribution> getProjectContributionByIDAndIteration(int project_id, int iteration_id) {
        Map<String, Object> map = new HashMap<>();
        String iteration = switchIteration(iteration_id);
        map.put("project_id", project_id);
        map.put("iteration", iteration);

        List<ProjectContribution> studentComposition_list =
                sqlSession.selectList("ValidCodeAggregation.selectProjectContributionByIDAndIteration", map);
        return studentComposition_list;
    }





    /**
     * @param author : 作者
     * @param iteration_id ： 迭代
     * @return ： 该作者在该迭代期间，不同作用文件的行数、有效行数
     */
    public List<StudentCode> getStudentValidByAuthorAndIteration(String author, int iteration_id) {
        Map<String, Object> map = new HashMap<>();
        String iteration = switchIteration(iteration_id);
        map.put("author", author);
        map.put("iteration", iteration);

        List<StudentCode> studentValid_list =
                sqlSession.selectList("ValidCodeAggregation.selectStudentValidByAuthorAndIteration", map);
        return studentValid_list;
    }


    /**
     * @param author : 作者
     * @param iteration_id ： 迭代
     * @return ： 该作者在该迭代期间，不同类型文件的行数、有效行数
     */
    public List<StudentCode> getStudentCompositionByAuthorAndIteration(String author, int iteration_id) {
        Map<String, Object> map = new HashMap<>();
        String iteration = switchIteration(iteration_id);
        map.put("author", author);
        map.put("iteration", iteration);

        List<StudentCode> studentComposition_list =
                sqlSession.selectList("ValidCodeAggregation.selectStudentCompositionByAuthorAndIteration", map);
        return studentComposition_list;
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

    private String switchTable(int iteration_id){
        String table_name = "valid_project_1";
        switch(iteration_id){
            case 1:
                table_name = "valid_project_1"; break;
            case 2:
                table_name = "valid_project_2"; break;
            case 3:
                table_name = "valid_project_3"; break;
        }
        return table_name;
    }



}
