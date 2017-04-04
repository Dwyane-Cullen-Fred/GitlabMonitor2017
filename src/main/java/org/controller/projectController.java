package org.controller;


import org.service.ProjectCommitService;
import org.service.StudentCommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(method = RequestMethod.GET, value = "/projectCommit/all")
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


}
