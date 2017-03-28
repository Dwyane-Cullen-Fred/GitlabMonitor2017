package org.service;

import org.bean.School;
import org.dao.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    public void SetSchoolDao(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    public School findSchoolByUcode(int ucode) {
        return schoolDao.getSchoolbyCode(ucode);
    }
}
