package com.lin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by linziyu on 2019/2/6.
 */

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "user/login5";
    }

}
