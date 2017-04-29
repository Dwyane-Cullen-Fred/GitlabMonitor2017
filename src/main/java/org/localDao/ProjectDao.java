package org.localDao;

import org.bean.ProjectInfo;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDao extends LocalBaseDaoImpl {

    /**
     * 根据项目id找到项目的名字
     * @param projectId : 项目id
     * @return ： string
     */
    public String selectProjectNameById(int projectId) {
        return sqlSession.selectOne("project.selectProjectNameById", projectId);
    }

    /**
     * 根据项目id找到项目基本信息
     * @param projectId : 项目id
     * @return : projectInfo
     */
    public ProjectInfo selectProjectById(int projectId) {
        return sqlSession.selectOne("project.selectProjectById", projectId);
    }
}
