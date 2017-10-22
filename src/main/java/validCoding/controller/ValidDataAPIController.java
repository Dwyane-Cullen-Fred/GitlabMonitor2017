package validCoding.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import validCoding.bean.*;
import validCoding.service.ValidCodeAggregationService;
import java.util.List;

/**
 * @author  王珺宇 131250044
 */

@Controller
public class ValidDataAPIController {

    @Autowired
    private ValidCodeAggregationService validCodeAggregationService;



    /**
     * @param project_id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同作用文件的行数
     */
    @RequestMapping(method = RequestMethod.GET, value = "/validCodingData/projectValid/{project_id}/iteration/{iteration_id}",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getProjectValid(@PathVariable("project_id") int project_id, @PathVariable("iteration_id") int iteration_id){

        List<ProjectCode> list = validCodeAggregationService.getProjectValid(project_id, iteration_id);
        return new Gson().toJson(list);
    }


    /**
     * @param project_id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同类型代码的行数
     */
    @RequestMapping(method = RequestMethod.GET, value = "/validCodingData/projectComposition/{project_id}/iteration/{iteration_id}",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getProjectComposition(@PathVariable("project_id") int project_id, @PathVariable("iteration_id") int iteration_id){

        List<ProjectCode> list = validCodeAggregationService.getProjectComposition(project_id, iteration_id);
        return new Gson().toJson(list);
    }


    /**
     * @param project_id : 项目id
     * @param iteration_id ： 迭代
     * @return ： 某迭代，不同学生对该项目的贡献
     */
    @RequestMapping(method = RequestMethod.GET, value = "/validCodingData/projectContribution/{project_id}/iteration/{iteration_id}",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getProjectContribution(@PathVariable("project_id") int project_id, @PathVariable("iteration_id") int iteration_id){

        List<ProjectContribution> list = validCodeAggregationService.getProjectContribution(project_id, iteration_id);
        return new Gson().toJson(list);
    }






    /**
     * @param author : 作者
     * @param iteration_id ： 迭代
     * @return ： 该作者在该迭代期间，不同作用文件的行数、有效行数
     */
    @RequestMapping(method = RequestMethod.GET, value = "/validCodingData/studentValid/{author}/iteration/{iteration_id}",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getStudentValid(@PathVariable("author") String author, @PathVariable("iteration_id") int iteration_id){

        List<StudentCode> list = validCodeAggregationService.getStudentValid(author, iteration_id);
        return new Gson().toJson(list);
    }


    /**
     * @param author : 作者
     * @param iteration_id ： 迭代
     * @return ： 该作者在该迭代期间，不同作用文件的行数、有效行数
     */
    @RequestMapping(method = RequestMethod.GET, value = "/validCodingData/studentComposition/{author}/iteration/{iteration_id}",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getStudentComposition(@PathVariable("author") String author, @PathVariable("iteration_id") int iteration_id){

        List<StudentCode> list = validCodeAggregationService.getStudentComposition(author, iteration_id);
        return new Gson().toJson(list);
    }




}
