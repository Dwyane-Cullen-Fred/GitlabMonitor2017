package validCoding.dao;

import org.springframework.stereotype.Repository;
import validCoding.bean.DailyCommit;
import validCoding.bean.ValidFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author   王珺宇 131250044
 */
@Repository
public class ValidFileDao extends BaseDaoImpl {


    /**
     * @param project_id 项目id
     * @param iteration_id 迭代id
     * @param type 选中的代码类型
     */
    public List<ValidFile> getProjectValidFile(int project_id, int iteration_id, String type) {
        Map<String, Object> map = new HashMap<>();
        String table_name = switchTable(iteration_id);
        map.put("project_id", project_id);
        map.put("table_name", table_name);
        map.put("type", type);

        List<ValidFile> projectValidFile_list =
                sqlSession.selectList("ValidFile.selectProjectValidFileByAuthorAndIterationAndType", map);
        return projectValidFile_list;
    }




    /**
     * @param author 学生姓名
     * @param iteration_id 迭代id
     * @param type 选中的代码类型
     * @return 某学生某迭代，写的某种有码版本（排序标准：该学生迭代器件对同一个文件总计贡献了多少代码；取值量：取前15）
     */
    public List<ValidFile> getStudentValidFile(String author, int iteration_id, String type) {
        Map<String, Object> map = new HashMap<>();
        String iteration = switchIteration(iteration_id);
        map.put("author", author);
        map.put("iteration", iteration);
        map.put("type", type);

        List<ValidFile> studentValidFile_list =
                sqlSession.selectList("ValidFile.selectStudentValidFileByAuthorAndIterationAndType", map);
        return studentValidFile_list;
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
