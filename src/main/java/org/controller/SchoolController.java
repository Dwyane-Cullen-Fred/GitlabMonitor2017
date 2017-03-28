package org.controller;


import org.bean.School;
import org.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(method = RequestMethod.GET, value = "/school/{ucode}")
    public String getSchoolByUcode(@PathVariable int ucode, ModelMap modelMap) {
        School school = schoolService.findSchoolByUcode(ucode);
        modelMap.addAttribute("ucode", school.getUcode());
        modelMap.addAttribute("abbreviation", school.getAbbreviation());
        modelMap.addAttribute("name", school.getName());
        return "school";
    }
}
