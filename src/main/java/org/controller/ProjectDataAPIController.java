package org.controller;

import org.localService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ProjectDataAPIController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET, value = "/data/project/{projectId}/projectName")
    @ResponseBody
    public String getProjectNameById(@PathVariable int projectId) {
        return projectService.getProjectNameById(projectId);
    }
}
