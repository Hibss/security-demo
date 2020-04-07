package com.czkj.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author steven.sheng
 * @Date 2020/4/7/00714:56
 */
@Controller
public class PageController {
    private final String PREFIX = "page/";

    @GetMapping({"/welcome", "/"})
    public String home() {
        return "welcome";
    }

    /**
     * 登陆页
     *
     * @return
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }




    /**
     * level1页面映射
     *
     * @param path
     * @return
     */
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path) {
        return PREFIX + "level1/" + path;
    }

    /**
     * level2页面映射
     *
     * @param path
     * @return
     */
    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path") String path) {
        return PREFIX + "level2/" + path;
    }

    /**
     * level3页面映射
     *
     * @param path
     * @return
     */
    @GetMapping("/level3/{path}")
    public String level3(@PathVariable("path") String path) {
        return PREFIX + "level3/" + path;
    }

}
