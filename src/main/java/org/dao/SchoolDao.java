package org.dao;

import org.bean.School;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolDao extends BaseDaoImpl{

    public SchoolDao() {
        super();
    }

    public School getSchoolbyCode(int ucode) {
        return sqlSession.selectOne("school.selectSchoolByUcode", ucode);
    }
}
