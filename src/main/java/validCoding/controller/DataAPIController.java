package validCoding.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import validCoding.bean.AggregatedProjectCode;
import validCoding.bean.AggregatedStudentCode;
import validCoding.bean.ProjectInfo;
import validCoding.bean.StudentInfo;
import validCoding.service.CodeAggregationService;
import validCoding.service.InfoService;
import java.util.List;

@Controller
public class DataAPIController {

    @Autowired
    private CodeAggregationService codeAggregationService;

    @Autowired
    private InfoService infoService;


    /**
     * @param id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同类型文件的行数、有效行数
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/validCoding/project/{id}/iteration/{iteration_id}")
    public @ResponseBody
    String geAggregatedProjectCodeByIDAndIteration(@PathVariable int id, @PathVariable int iteration_id){
        List<AggregatedProjectCode> list = codeAggregationService.getAggregatedProjectCodeByNameAndIteration(id, iteration_id);
        return new Gson().toJson(list);
    }

    /**
     * @param id : 项目id
     * @return : 项目基本信息
     */
    @RequestMapping(method = RequestMethod.GET, value  = "/data/validCoding/projectInfo/{id}")
    public @ResponseBody
    String getProjectBasicInfo(@PathVariable int id) {
        List<ProjectInfo> list = infoService.getProjectBasicInfoByID(id);
        return new Gson().toJson(list);
    }


    /**
     * @param author : 学生姓名
     * @param iteration_id ： 迭代id
     * @return ： 该作者在该迭代期间，不同类型文件的行数、有效行数
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/validCoding/student/{author}/iteration/{iteration}")
    public @ResponseBody
    String getAggregatedStudentCodeByAuthorAndIteration(@PathVariable("author") String author, @PathVariable("iteration") int iteration_id){
        String iteration = "迭代";

        switch(iteration_id) {
            case (1):
                iteration = "迭代一";
                break;
            case (2):
                iteration = "迭代二";
                break;
            case (3):
                iteration = "迭代三";
                break;
        }

        List<AggregatedStudentCode> list = codeAggregationService.getAggregatedStudentCodeByAuthorAndIteration(author, iteration);
        return new Gson().toJson(list);
    }



    /**
     * @param author : 学生姓名
     * @return : 学生基本信息
     */
    @RequestMapping(method = RequestMethod.GET, value  = "/data/validCoding/studentInfo/{author}")
    public @ResponseBody
    String getStudentInfo(@PathVariable String author) {
        List<StudentInfo> list = infoService.getStudentInfoByAuthor(author);
        return new Gson().toJson(list);
    }


}
