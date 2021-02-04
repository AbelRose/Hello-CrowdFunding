package com.matrix.crowdfunding.service;

import com.matrix.crowdfunding.bean.TMenu;
import com.matrix.crowdfunding.mapper.TMenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author yihaosun
 * @Date 2/4/2021 22:21
 */

@Service
public class TMenuServiceImpl implements TMenuService {

    Logger log = LoggerFactory.getLogger(TMenuServiceImpl.class);

    @Autowired
    TMenuMapper menuMapper;

    @Override
    public List<TMenu> listMenuAll() {

        List<TMenu> menuList = new ArrayList<>();  // 只存放父菜单但是将children属性赋值
        List<TMenu> allList = menuMapper.selectByExample(null);  // 返回的是整个list集合

        HashMap<Integer, TMenu> cache = new HashMap<>();  // 存放父的id和实体


        for (TMenu tMenu : allList) {
            if (tMenu.getPid() == 0) {  // 父
                menuList.add(tMenu);
                cache.put(tMenu.getId(), tMenu);
            }
        }

        for (TMenu tMenu : allList) {
            if (tMenu.getPid() != 0) {  // 子
                TMenu parent = cache.get(tMenu.getPid());  // 找到子的父
                parent.getChildren().add(tMenu);  // 组合父子关系
            }
        }

        log.debug("菜单={}", menuList);

        return menuList; // 返回的是父以及其对应的全部子
    }
}
