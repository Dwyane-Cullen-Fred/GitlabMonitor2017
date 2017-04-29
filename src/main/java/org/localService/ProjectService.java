package org.localService;

import org.localDao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public String getProjectNameById(int projectId) {
        return projectDao.selectProjectNameById(projectId).split("/")[1];
    }
}
