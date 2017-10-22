package validCoding.dao;

import org.springframework.stereotype.Repository;
import validCoding.bean.ProjectInfo;
import validCoding.bean.StudentInfo;

import java.util.List;

/**
 * @author   王珺宇 131250044
 */
@Repository
public class InfoDao extends BaseDaoImpl {
    /**
     * @param id : 项目id
     * @return ： 项目基本信息，包括id，名字，url，组号
     */
    public List<ProjectInfo> selectProjectBasicInfoByID(int id) {
        List<ProjectInfo> result = sqlSession.selectList("Info.selectProjectBasicInfoByID", id);
        return  result;
    }


    /**
     * @param author : 学生姓名
     * @return ： 学生信息，包括姓名，项目名，项目名，项目url，组号
     */
    public List<StudentInfo> selectStudentInfoByAuthor(String author) {

        List<StudentInfo> result = sqlSession.selectList("Info.selectStudentInfoByAuthor", author);
        return  result;
    }

}
