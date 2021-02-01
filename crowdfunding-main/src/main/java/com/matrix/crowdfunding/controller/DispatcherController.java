package com.matrix.crowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 负责转发 相当于共有功能
 */
@Controller
public class DispatcherController {

    Logger log = LoggerFactory.getLogger(DispatcherController.class);

    @RequestMapping("/index")  // /index中的/杠 代表http://localhost:8080/crowdfunding_main_war/  逻辑路径
    public String index() {
        log.debug("跳转到系统的主页面...");
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        log.debug("跳转到登录的主页面...");
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String loginacct, String userpswd) {
        log.debug("开始登陆...");

        log.debug("loginacct={}", loginacct);
        log.debug("userpswd={}", userpswd);

        return "main";
    }
}
