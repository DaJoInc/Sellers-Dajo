package com.dajo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by JoDa on 29/05/2017.
 */
@Controller
@RequestMapping("/profilePro")

public class ProfileController {
    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        return "ProfileManagement";
    }
}