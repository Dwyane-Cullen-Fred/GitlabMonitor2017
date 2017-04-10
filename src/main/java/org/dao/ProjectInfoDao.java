package org.dao;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectInfoDao extends BaseDaoImpl{

    /**
     *
     * @return : 所有项目id的集合
     */
    public List<Integer> selectProjectAllId() {
        return sqlSession.selectList("projectInfo.selectProjectAllId");
    }

}
