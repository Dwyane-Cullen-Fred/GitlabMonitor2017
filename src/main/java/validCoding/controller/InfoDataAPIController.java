package validCoding.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import validCoding.bean.ProjectInfo;
import validCoding.bean.StudentInfo;
import validCoding.service.InfoService;

import java.util.List;

/**
 * @author  王珺宇 131250044
 */

@Controller
public class InfoDataAPIController {
    @Autowired
    private InfoService infoService;



    /**
     * @param id : 项目id
     * @return : 项目基本信息
     */
    @RequestMapping(method = RequestMethod.GET, value  = "/validCodingData/projectInfo/{id}")
    public @ResponseBody
    String getProjectBasicInfo(@PathVariable int id) {
        List<ProjectInfo> list = infoService.getProjectBasicInfoByID(id);
        return new Gson().toJson(list);
    }


    /**
     * @param author : 学生姓名
     * @return : 学生基本信息
     */
    @RequestMapping(method = RequestMethod.GET, value  = "/validCodingData/studentInfo/{author}")
    public @ResponseBody
    String getStudentInfo(@PathVariable String author) {
        List<StudentInfo> list = infoService.getStudentInfoByAuthor(author);
        return new Gson().toJson(list);
    }
}
