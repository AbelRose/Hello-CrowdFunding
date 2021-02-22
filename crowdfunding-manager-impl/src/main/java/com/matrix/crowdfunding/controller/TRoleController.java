package com.matrix.crowdfunding.controller;

import com.matrix.crowdfunding.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author yihaosun
 * @Date 2/22/2021 22:19
 */
@Controller
public class TRoleController {

    @Autowired
    private TRoleService roleService;

    @RequestMapping("/role/index")  // 路径和数据库中的是一样的
    public String index() {
        return "role/index";  // 拼前缀和后缀
    }
}
