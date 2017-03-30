package org.dao;

import org.bean.StudentCommit;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * Created by XXH on 2017/3/29.
 */
@Repository
public class StudentCommitDao extends BaseDaoImpl{


    /**
     * @param id : 项目名称
     * @return ： 对应项目中学生每天的提交情况
     */
    public List<StudentCommit> selectStudentCommitById(int id) {
        return sqlSession.selectList("selectStudentCommitById", id);
    }

    /**
     * @param id : 项目名称
     * @param deadline_id ： deadline的id
     * @return ： 对应项目中学生的贡献度
     */
    public List<StudentCommit> selectStudentCommitByDeadline(int id, int deadline_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("deadline_id", deadline_id);
        return sqlSession.selectList("selectStudentCommitByDeadline", map);
    }
}
