package org.controller;

import com.google.gson.Gson;
import org.bean.ContributionAggregation;
import org.localService.CodeAggregationService;
import org.localService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class StudentDataAPIController {

    @Autowired
    private FileService fileService;

    @Autowired
    private CodeAggregationService codeAggregationService;

    @RequestMapping(method = RequestMethod.GET,
            value = "/data/project/{projectId}/iteration/{iteration}/contribution")
    @ResponseBody
    public String getContributionData(@PathVariable int projectId, @PathVariable int iteration) {
        Map<String, ContributionAggregation> map = fileService.getStudentContribution(projectId, iteration);
        return new Gson().toJson(map.values());
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/data/project/{projectId}/iteration/{iteration}/invalidFile/type/{type}")
    @ResponseBody
    public String getInvalidCommitByCommentAndBlank(@PathVariable int projectId,
                                                    @PathVariable int iteration, @PathVariable int type) {
        return new Gson().toJson(fileService.getInvalidFile(projectId, iteration, type));
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/data/project/{projectId}/iteration/{iteration}/getAggregation")
    @ResponseBody
    public String getAggregation(@PathVariable int projectId, @PathVariable int iteration) {
        return new Gson().toJson(codeAggregationService.getProjectCode(projectId, iteration));
    }


    /*
     @RequestMapping (method = RequestMethod.GET,
             value = "/data/project/{projectId}/iteration/{iteration}/contribution/{type}")
     @ResponseBody
     public String getContributionData(@PathVariable int projectId,
                                       @PathVariable int iteration, @PathVariable String type)
     {
         Map<String, Integer> map = fileService.getStudentContribution(projectId, iteration, type);
         return new Gson().toJson(map);
     }

     @RequestMapping (method = RequestMethod.GET,
            value = "/data/project/{projectId}/iteration/{iteration}/contributionFromOthers")
     @ResponseBody
     public String getContributionFromOthersData(@PathVariable int projectId, @PathVariable int iteration)
     {
         Map<String, Integer> map = fileService.getContributionFromOthers(projectId, iteration);
         return new Gson().toJson(map);
     }

    @RequestMapping (method = RequestMethod.GET,
            value = "/data/project/{projectId}/iteration/{iteration}/contributionToOthers")
    @ResponseBody
     public String getContributionToOthersData(@PathVariable int projectId, @PathVariable int iteration)
     {
         Map<String, Integer> map = fileService.getContributionToOthers(projectId, iteration);
         return new Gson().toJson(map);
     }

     @RequestMapping (method = RequestMethod.GET,
            value = "/data/project/{projectId}/iteration/{iteration}/contributionCommit")
     @ResponseBody
     public String getCommitContributionData(@PathVariable int projectId, @PathVariable int iteration)
     {
         Map<String, Integer> map = fileService.getCommitContribution(projectId, iteration);
         return new Gson().toJson(map);
     }
     */
}
