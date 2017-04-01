package org.dao;

import org.bean.DayCommit;
import org.bean.ProjectCommit;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by XXH on 2017/3/29.
 */

@Repository
public class ProjectCommitDao extends BaseDaoImpl{

    /**
     * param : none
     * @return : 每天的项目提交情况
     */
    public List<DayCommit> selectProjectCommitGroupByDay() {
        return sqlSession.selectList("projectCommit.selectProjectCommitGroupByDay");
    }

    /**
     * @param id ： 项目的id
     * @return : 对应项目的提交情况
     */
    public List<ProjectCommit> selectProjectCommitById(int id) {
        return sqlSession.selectList("projectCommit.selectProjectCommitGroupById", id);
    }

    /**
     * @param id : 项目id
     * @param deadline_id ： 迭代id
     * @return ： 对应迭代项目提交情况
     */
    public List<ProjectCommit> selectProjectIterationCommit(int id, int deadline_id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        map.put("deadline_id", deadline_id);
        return sqlSession.selectList("projectCommit.selectProjectIterationCommit", map);
    }
}
