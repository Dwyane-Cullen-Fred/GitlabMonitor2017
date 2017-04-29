package validCoding.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import validCoding.service.CodeAggregationService;

/**
 * @author  王珺宇 131250044
 */
@Controller
public class CodingAnalysisController {

    @Autowired
    private CodeAggregationService codeAggregationService;

    /**
     * @return ： 显示的项目代码分析页面
     */
    @RequestMapping(method = RequestMethod.GET, value = "/validCoding/project/detail")
    public String showProjectCoding() {
        return "projectCoding";
    }

    /**
     * @return ： 显示的学生代码分析页面
     */
    @RequestMapping(method = RequestMethod.GET, value = "/validCoding/student/detail")
    public String showStudentCoding() {
        return "studentCoding";
    }



}
