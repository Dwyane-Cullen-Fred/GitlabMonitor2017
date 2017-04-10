package org.service;

import org.dao.ProjectInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XXH on 2017/4/9.
 */

@Service
public class ProjectInfoService {

    @Autowired
    private ProjectInfoDao projectInfoDao;

    /**
     *
     * @return : 所有项目的id
     */
    public List<Integer> getProjectAllId() {
        return projectInfoDao.selectProjectAllId();
    }
}
