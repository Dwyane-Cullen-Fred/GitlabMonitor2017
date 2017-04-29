package validCoding.dao;

import org.springframework.stereotype.Repository;
import validCoding.bean.AggregatedProjectCode;
import validCoding.bean.AggregatedStudentCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author   王珺宇 131250044
 */
@Repository
public class CodeAggregationDao extends BaseDaoImpl  {

    /**
     * @param id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同类型文件的行数、有效行数
     */
    public List<AggregatedProjectCode> getAggregatedProjectCodeByNameAndIteration(int id, int iteration_id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        map.put("iteration", iteration_id);

        List<AggregatedProjectCode> result =  sqlSession.selectList("CodeAggregation.selectAggregatedProjectCodeByIDAndIteration", map);

        return result;
    }

    /**
     * @param author : 作者
     * @param iteration ： 迭代
     * @return ： 该作者在该迭代期间，不同类型文件的行数、有效行数
     */
    public List<AggregatedStudentCode> selectAggregatedStudentCodeByAuthorAndIteration(String author, String iteration) {
        Map<String, String> map = new HashMap<>();
        map.put("author", author);
        map.put("iteration", iteration);

        List<AggregatedStudentCode> result =  sqlSession.selectList("CodeAggregation.selectAggregatedStudentCodeByAuthorAndIteration", map);

        return result;
    }
}
