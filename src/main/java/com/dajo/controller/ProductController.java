package com.dajo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by JoDa on 29/05/2017.
 */
@Controller
@RequestMapping("/productPro")
public class ProductController {
    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        return "ProductManagement";
    }
}
