package com.matrix.crowdfunding.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matrix.crowdfunding.bean.TAdmin;
import com.matrix.crowdfunding.service.TAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yihaosun
 * @Date 2/6/2021 13:43
 */

@Controller
public class TAdminController {

    @Autowired
    TAdminService adminService;

    Logger log = LoggerFactory.getLogger(TAdminController.class);

    @RequestMapping("/admin/index")
    public String index(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "3")Integer pageSize,
                        Model model){  // @RequestParam value 是后面的变量名   Model model 是请求域(因为页面不一样所以放到session域中是无意义的)

        PageHelper.startPage(pageNum, pageSize);  // 线程绑定 分页的信息
        Map<String, Object> paramMap = new HashMap<>(); // 封装参数用的
        PageInfo<TAdmin> page = adminService.listAdminPage(paramMap);  // 分页查询
        model.addAttribute("page", page);  // 放到请求域中
        return "admin/index";
    }

    @RequestMapping("/admin/toAdd")
    public String toAdd() {

        return "admin/add";
    }

    @RequestMapping("/admin/doAdd")
    public String doAdd(TAdmin admin) {
        adminService.saveTAdmin(admin);
//        return "redirect:/admin/index";
        return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;  // 分页合理话 为了新增的在第一页显示

    }

    @RequestMapping("/admin/toUpdate")
    public String toUpdate(Integer id, Model model) {
        TAdmin admin = adminService.getTAdminById(id);
        model.addAttribute("admin", admin);  // Model model 是一个请求域 并放到主页面
        return "admin/update";
    }

    @RequestMapping("/admin/doUpdate")
    public String doUpdate(TAdmin admin) {
        adminService.updateTAdmin(admin);
        return "redirect:/admin/index";
    }
}
