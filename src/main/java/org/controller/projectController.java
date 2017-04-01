package org.controller;

import com.google.gson.Gson;
import org.bean.DayCommit;
import org.service.ProjectCommitService;
import org.service.StudentCommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class projectController {

    @Autowired
    private ProjectCommitService projectCommitService;

    @Autowired
    private StudentCommitService studentCommitService;
    /**
     * @return ： 显示的页面
     */
    @RequestMapping(method = RequestMethod.GET, value = "/projectCommit/All")
    public String showCommit() {
        return "allCommit";
    }

    /**
     * @return : 项目信息显示页面
     */
    @RequestMapping(method = RequestMethod.GET, value = "/projectCommit/detail")
    public String showProjectCommit() {
        return "projectCommit";
    }


/*---------------------------------------------------------------------------------------------------------------*/

    /**
     * @return ： 每天所有项目提交信息的json格式
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/dayCommitData")
    public @ResponseBody String getDayCommitData(){
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


    @RequestMapping(method = RequestMethod.GET, value = "/data/project/{id}/studentCommit/iteration/{iteration_id}")
    public @ResponseBody String getIterationStudentCommitData(@PathVariable int id, @PathVariable int iteration_id) {
        return new Gson().toJson(studentCommitService.selectStudentIterationCommit(id, iteration_id));
    }
}
