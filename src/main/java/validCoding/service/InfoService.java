package validCoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validCoding.bean.ProjectInfo;
import validCoding.bean.StudentInfo;
import validCoding.dao.InfoDao;

import java.util.List;

/**
 * @author   王珺宇 131250044
 */
@Service
public class InfoService {

    @Autowired
    private InfoDao infoDao;

    /**
     * @param id : 项目id
     * @return ： 项目基本信息
     */
    public List<ProjectInfo> getProjectBasicInfoByID(int id) {

        return infoDao.selectProjectBasicInfoByID(id);
    }


    /**
     * @param author : 学生姓名
     * @return ： 学生基本信息
     */
    public List<StudentInfo> getStudentInfoByAuthor(String author) {

        return infoDao.selectStudentInfoByAuthor(author);
    }

}
