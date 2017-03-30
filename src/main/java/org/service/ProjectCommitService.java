package org.service;

import org.bean.DayCommit;
import org.bean.ProjectCommit;
import org.dao.ProjectCommitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XXH on 2017/3/29.
 */

@Service
public class ProjectCommitService {

    @Autowired
    private ProjectCommitDao projectCommitDao;

    /**
     * param : none
     * @return : 每天的项目提交情况
     */
    public List<DayCommit> getProjectCommitGroupByDay() {
        return projectCommitDao.selectProjectCommitGroupByDay();
    }

    /**
     * @param id ： 项目的id
     * @return : 对应项目的提交情况
     */
    public List<ProjectCommit> getProjectCommitById(int id) {
        return projectCommitDao.selectProjectCommitById(id);
    }
}
