package com.matrix.crowdfunding.controller;

import com.matrix.crowdfunding.bean.TMenu;
import com.matrix.crowdfunding.service.TAdminService;
import com.matrix.crowdfunding.service.TMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.matrix.crowdfunding.util.Const;
import org.springframework.ui.Model;
import com.matrix.crowdfunding.bean.TAdmin;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责转发 相当于共有功能
 */
@Controller
public class DispatcherController {

    Logger log = LoggerFactory.getLogger(DispatcherController.class);

    @Autowired
    TMenuService menuService;

    // 将sevice注入
    @Autowired
    TAdminService adminService;

    @RequestMapping("/index")  // /index中的/杠 代表http://localhost:8080/crowdfunding_main_war/  逻辑路径
    public String index() {
        log.debug("跳转到系统的主页面...");
        return "index";
    }

    @RequestMapping("/toLogin")
    public String login() {
        log.debug("跳转到登录的主页面...");
        return "login";
    }

    @RequestMapping("/main")
    public String main(HttpSession session) {
        log.debug("跳转后台系统的main页面...");

        // 如果没有session 那么要去重新登陆
        if (session == null) {
            return "redirect:/toLogin";
        }
        // 存放父菜单
        // 优化 -> 不必每次都从数据库中查找 如果session域中有的话直接从session域中拿数据
        List<TMenu> menuList  = (List<TMenu>) session.getAttribute("menuList");
        if (menuList == null) { // 若session域中没有
            menuList = menuService.listMenuAll();
            session.setAttribute("menuList", menuList);
        }
        // session 域中有了 就直接去main页面
        return "main";
    }

//    @RequestMapping("/doLogin")
//    public String doLogin(String loginacct, String userpswd, HttpSession session, Model model) {
//        log.debug("开始登陆...");
//
//        log.debug("loginacct={}", loginacct);
//        log.debug("userpswd={}", userpswd);
//
//        /** 如果直接将loginacct userpswd传进去的话 有点不好 如果以后参数多了或者少了 直接put 接口是不用变的
//         *  所以打一个包
//         *  adminService.getTAdminByLogin(loginacct, userpswd);
//         * */
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("loginacct", loginacct);
//        paramMap.put("userpswd", userpswd);
//
//        try {
//            TAdmin admin = adminService.getTAdminByLogin(paramMap);
//            // 将信息放到session域中
//            session.setAttribute(Const.LOGIN_ADMIN, admin);  // Const.LOGIN_ADMIN "loginAdmin"  传到main.jsp中
//            log.debug("登录成功...");
////            return "main";  避免表单重复提交 采用重定向
//            return "redirect:/main";  // session是可以跨页面的所以可以取到数据
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.debug("登录失败-{}...", e.getMessage());
//            model.addAttribute(Const.LOGIN_ERROR , e.getMessage());  // 为了给前端取出显示的
//            return "login";
//        }
//    }

//    @RequestMapping("/logout")
//    public String logout(HttpSession session) {
//        log.debug("注销系统...");
//        if (session != null) {  // 为了反正时间过长30min session失效 而发生空指针异常错误
//            session.removeAttribute(Const.LOGIN_ADMIN);  // 把session中的admin删除掉
//            session.invalidate();  // 销毁session
//        }
//        return "redirect:index";  // 重定向过去
//    }
}
