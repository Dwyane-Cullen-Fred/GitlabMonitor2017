package org.controller;

import com.google.gson.Gson;
import org.bean.DayCommit;
import org.bean.ProjectCommit;
import org.service.ProjectCommitService;
import org.service.StudentCommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
public class projectController {

    @Autowired
    private ProjectCommitService projectCommitService;

    @Autowired
    private StudentCommitService studentCommitService;

    /**
     *
     * @return ： 每天提交信息的json格式
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/dayCommitData")
    public @ResponseBody String getDayCommitData(){
        List<DayCommit> list = projectCommitService.getProjectCommitGroupByDay();
        return new Gson().toJson(list);
    }

    /**
     * @return ： 显示的页面
     */
    @RequestMapping(method = RequestMethod.GET, value = "/projectCommit/home")
    public String showCommit() {
        return "allCommit";
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
     * @param id
     * @param deadline_id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/data/project/{id}/deadline/{deadline_id}")
    public @ResponseBody String getStudentCommitData(@PathVariable int id, @PathVariable int deadline_id) {
        return new Gson().toJson(studentCommitService.getStudentCommitByDeadline(id, deadline_id));
    }

    /**
     * @param id : 项目id
     * @return : 项目信息显示页面
     */
    @RequestMapping(method = RequestMethod.GET, value = "/projectCommit/{id}")
    public String showProjectCommit(@PathVariable int id) {
        return "projectCommit";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/base")
    public String base() {
        return "base";
    }
}
