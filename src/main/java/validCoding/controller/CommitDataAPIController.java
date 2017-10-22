package validCoding.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import validCoding.bean.DailyCommit;
import validCoding.bean.ProjectCode;
import validCoding.service.DailyCommitService;

import java.util.List;

/**
 * Created by Dwyane Cullen Fred on 2017/5/14.
 */

@Controller
public class CommitDataAPIController {

    @Autowired
    DailyCommitService dailyCommitService;


    /**
     * @param project_id 项目id
     * @param iteration_id 迭代id
     * @param screenOut_list 筛去的代码类型
     * @return 某项目某个迭代，筛去某些代码类型以后提每日提交数量
     */
    @RequestMapping(method = RequestMethod.GET, value  = "/validCodingData/projectDailyCommit/GetJson",
            produces = "application/json; charset=utf-8")
    // url形式"/validCodingData/projectDailyCommit/GetJson?project_id={project_id}&iteration_id={iteration_id}&screenOut=xxx,yyy,zzz"
    public @ResponseBody
    String getProjectDailyCommit( @RequestParam("project_id") int project_id,
                                  @RequestParam("iteration_id") int iteration_id,
                                  @RequestParam("screenOut") String[] screenOut_list) {
        List<DailyCommit> list = dailyCommitService.getProjectDailyCommit(project_id, iteration_id, screenOut_list);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(list);
    }



    /**
     * @param author 学生姓名
     * @param iteration_id 迭代id
     * @param screenOut_list 筛去的代码类型
     * @return 某学生某个迭代，筛去某些代码类型以后提每日提交数量
     */
    @RequestMapping(method = RequestMethod.GET, value  = "/validCodingData/studentDailyCommit/GetJson",
            produces = "application/json; charset=utf-8")
    // url形式"/validCodingData/studentDailyCommit/GetJson?author={author}&iteration_id={iteration_id}&screenOut=xxx,yyy,zzz"
    public @ResponseBody
    String getStudentDailyCommit( @RequestParam("author") String author,
                                @RequestParam("iteration_id") int iteration_id,
                                @RequestParam("screenOut") String[] screenOut_list) {
        List<DailyCommit> list = dailyCommitService.getStudentDailyCommit(author, iteration_id, screenOut_list);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(list);
    }




}
