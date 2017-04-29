package org.localDao;

import org.bean.AggregatedProjectCode;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CodeAggregationDao extends LocalBaseDaoImpl  {

    /**
     * @param id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同类型文件的行数、有效行数
     */
    public List<AggregatedProjectCode> selectAggregatedCodeFileByIDAndIteration(int id, int iteration_id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        map.put("iteration", iteration_id);
        return sqlSession.selectList("CodeAggregation.selectAggregatedCodeFileByIDAndIteration", map);
    }
}