package validCoding.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import validCoding.bean.ValidFile;
import validCoding.service.ValidFileService;

import java.util.List;

/**
 * @author   王珺宇 131250044
 */
@Controller
public class FileDataAPIController {

    @Autowired
    ValidFileService validFileService;


    /**
     * @param project_id 项目id
     * @param iteration_id 迭代id
     * @param type 选中的代码类型
     * @return 某项目某迭代，某种有码版本（排序标准：该项目单个文件的总行数；取值量：取前20）
     */
    @RequestMapping(method = RequestMethod.GET, value  = "/validCodingData/projectValidFile/GetJson",
            produces = "application/json; charset=utf-8")
    // url format ："/validCodingData/projectValidFile/GetJson?project_id={project_id}&iteration_id={iteration_id}&selectedValidType=xxx"
    public @ResponseBody
    String getProjectValidFile( @RequestParam("project_id") int project_id,
                                @RequestParam("iteration_id") int iteration_id,
                                @RequestParam("selectedValidType") String type) {
        List<ValidFile> list = validFileService.getProjectValidFile(project_id, iteration_id, type);
        return new Gson().toJson(list);
    }




    /**
     * @param author 学生姓名
     * @param iteration_id 迭代id
     * @param type 选中的代码类型
     * @return 某学生某迭代，写的某种有码版本（排序标准：该学生迭代器件对同一个文件总计贡献了多少代码；取值量：取前15）
     */
    @RequestMapping(method = RequestMethod.GET, value  = "/validCodingData/studentValidFile/GetJson",
            produces = "application/json; charset=utf-8")
    // url形式"/validCodingData/studentValidFile/GetJson?author={author}&iteration_id={iteration_id}&selectedValidType=xxx"
    public @ResponseBody
    String getStudentValidFile( @RequestParam("author") String author,
                                  @RequestParam("iteration_id") int iteration_id,
                                  @RequestParam("selectedValidType") String type) {
        List<ValidFile> list = validFileService.getProjectValidFile(author, iteration_id, type);
        return new Gson().toJson(list);
    }

}
