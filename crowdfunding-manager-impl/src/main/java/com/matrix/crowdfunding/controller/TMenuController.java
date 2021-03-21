package com.matrix.crowdfunding.controller;

import com.matrix.crowdfunding.bean.TMenu;
import com.matrix.crowdfunding.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * 自关联表使用场景: 菜单的形式 或者一条线上的那种结构
 *
 * @Author yihaosun
 * @Date 3/1/2021 22:31
 */
@Controller
public class TMenuController {


    @Autowired
    private TMenuService menuService;

    @RequestMapping("/menu/index")
    public String index() {
        return "menu/index";
    }

    /**
     * @ResponseBody 以json的方式返回
     */
    @ResponseBody
    @RequestMapping("/menu/loadTree")
    public List<TMenu> loadTree() {
        return menuService.listMenuAllTree();
    }

    @ResponseBody
    @RequestMapping("/menu/doAdd")
    public String doAdd(TMenu menu) {
        menuService.saveTMenu(menu);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/menu/doUpdate")
    public String doUpdate(TMenu menu) {
        menuService.updateTMenu(menu);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/menu/doDelete")
    public String doDelete(Integer id) {
        menuService.deleteTMenu(id);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/menu/getMenuById")
    public TMenu getMenuById(Integer id) {
        TMenu menu = menuService.getMenuById(id);
        return menu;
    }

}
