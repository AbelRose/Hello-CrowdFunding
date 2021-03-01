package com.matrix.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author yihaosun
 * @Date 3/1/2021 22:31
 */
@Controller
public class TMenuController {

    @RequestMapping("/menu/index")
    public String index() {
        return "menu/index";
    }

}
