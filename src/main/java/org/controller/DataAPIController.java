package org.controller;

import com.google.gson.Gson;
import org.bean.DayCommit;
import org.service.ProjectCommitService;
import org.service.ProjectInfoService;
import org.service.StudentCommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DataAPIController {

    @Autowired
    private ProjectCommitService projectCommitService;

    @Autowired
    private StudentCommitService studentCommitService;

    @Autowired
    private ProjectInfoService projectInfoService;

    /**
     * @return ： 每天所有项目提交信息的json格式
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/dayCommitData")
    public @ResponseBody
    String getDayCommitData(){
        List<DayCommit> list = projectCommitService.getProjectCommitGroupByDay();
        return new Gson().toJson(list);
    }

    /**
     * @param id : 项目id
     * @return : 项目提交信息的json格式
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/project/{id}")
    public @ResponseBody String getProjectCommitData(@PathVariable int id) {
        return new Gson().toJson(projectCommitService.getProjectCommitById(id));
    }

    /**
     * @param id : 项目id
     * @param iteration_id : 迭代的id
     * @return ： 项目迭代的提交信息
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/project/{id}/iteration/{iteration_id}")
    public @ResponseBody String getProjectIterationCommit(@PathVariable int id, @PathVariable int iteration_id) {
        return new Gson().toJson(projectCommitService.getProjectIterationCommit(id, iteration_id));
    }

    /**
     * @param id : 项目id
     * @return ： 项目最终的学生提交情况
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/project/{id}/studentCommit")
    public @ResponseBody String getStudentCommitData(@PathVariable int id) {
        return new Gson().toJson(studentCommitService.getStudentCommit(id));
    }

    /**
     *
     * @param id : 项目id
     * @param iteration_id ： 迭代的id
     * @return ： 项目每次迭代学生的提交情况
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/project/{id}/studentCommit/iteration/{iteration_id}")
    public @ResponseBody String getIterationStudentCommitData(@PathVariable int id, @PathVariable int iteration_id)
    {
        return new Gson().toJson(studentCommitService.getStudentIterationCommit(id, iteration_id));
    }

    /**
     *
     * @param id ： 项目的id
     * @return ： 项目中学生的联系
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/project/{id}/studentConnection")
    public @ResponseBody String getStudentConnection(@PathVariable int id) {
        return new Gson().toJson(studentCommitService.getStudentConnection(id));
    }

    /**
     * @param id : 项目中id
     * @return ： 项目中学生的有效提交代码
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/project/{id}/studentValidCommit")
    public @ResponseBody String getStudentValidCommit(@PathVariable int id) {
        return new Gson().toJson(studentCommitService.getStudentValidCommit(id));
    }


    /**
     *
     * @return : 返回所有的项目的id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/project/allId")
    public @ResponseBody String getProjectAllId() {
        return new Gson().toJson(projectInfoService.getProjectAllId());
    }
}
