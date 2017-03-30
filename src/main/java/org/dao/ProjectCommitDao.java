package org.dao;

import org.bean.DayCommit;
import org.bean.ProjectCommit;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return sqlSession.selectList("selectProjectCommitGroupById", id);
    }
}
