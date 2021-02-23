package com.matrix.crowdfunding.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matrix.crowdfunding.bean.TRole;
import com.matrix.crowdfunding.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

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

    /**
     * @ResponseBody 注解 -> 启用了消息转换器
     * 1. 如果返回结果为对象（Entity Class,List,Map..）类型：启用这个转换器->MappingJackson2HttpMessageConverter
     * 将对象序列化为json串，使用Jackson组件转换
     *
     * 2. 如果返回结果为String类型：启用这个转换器->StringHttpMessageConverter  将字符串原样输出。
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/loadData")  // 路径和数据库中的是一样的
    public PageInfo<TRole> loadData(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 因为参数还不确定 所以惨书部分用一个map去映射
        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put();
        PageInfo<TRole> page = roleService.listRolePage(paramMap);
//        return page;  // 不用做异步解析
        //@ResponseBody注解 不用放在session请求域和作用域中 直接以一个json串返回了   不加的话需要做视图解析:拼前缀和后缀
        return page;
    }
}
