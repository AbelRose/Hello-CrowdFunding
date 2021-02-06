package com.matrix.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matrix.crowdfunding.bean.TAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(TAdminController.class);

    @RequestMapping("/admin/index")
    public String index(@RequestParam(value="pageNum",required=false,defaultValue="1") Integer pageNum,
                        @RequestParam(value="pageSize",required=false,defaultValue="2") Integer pageSize,
                        Model model,
                        @RequestParam(value="condition",required=false,defaultValue="") String condition) {

        log.debug("pageNum={}",pageNum);
        log.debug("pageSize={}",pageSize);
        log.debug("condition={}",condition);

        PageHelper.startPage(pageNum, pageSize);  //线程绑定

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("condition", condition);

//        PageInfo<TAdmin> page = adminService.listAdminPage(paramMap);

//        model.addAttribute("page", page);

        return "admin/index";
    }
}
