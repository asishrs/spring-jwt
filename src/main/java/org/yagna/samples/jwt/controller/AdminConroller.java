package org.yagna.samples.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asish on 3/29/16.
 */
@RestController
public class AdminConroller {
    @RequestMapping("/admin")
    public String admin(){
        return "WIP -Admin";
    }

    @RequestMapping("/login-1")
    public String login(){
        return "WIP -Login";
    }

    @RequestMapping(value = "/ping")
    public String ping(){
        return "I am up";
    }
}
