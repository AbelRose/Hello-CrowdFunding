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
        // 只存放父菜单但是将children属性赋值
        List<TMenu> menuList = new ArrayList<>();
        // 返回的是整个list集合
        List<TMenu> allList = menuMapper.selectByExample(null);
        // 存放父的id和实体
        HashMap<Integer, TMenu> cache = new HashMap<>();

        // 父
        for (TMenu tMenu : allList) {
            if (tMenu.getPid() == 0) {
                menuList.add(tMenu);
                cache.put(tMenu.getId(), tMenu);
            }
        }
        // 子
        for (TMenu tMenu : allList) {
            if (tMenu.getPid() != 0) {
                // 找到子的父
                TMenu parent = cache.get(tMenu.getPid());
                // 组合父子关系
                parent.getChildren().add(tMenu);
            }
        }

        log.debug("菜单={}", menuList);

        // 返回的是父以及其对应的全部子
        return menuList;
    }

    @Override
    public List<TMenu> listMenuAllTree() {
        return menuMapper.selectByExample(null);
    }

    @Override
    public void saveTMenu(TMenu menu) {
        /**
         * 如果选择insert 那么所有的字段都会添加一遍即使没有值
         * 如果使用inserSelective就会只给有值的字段赋值（会对传进来的值做非空判断）
         */
        menuMapper.insertSelective(menu);
    }

    @Override
    public TMenu getMenuById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateTMenu(TMenu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void deleteTMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
