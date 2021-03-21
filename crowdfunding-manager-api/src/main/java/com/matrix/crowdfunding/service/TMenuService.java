package com.matrix.crowdfunding.service;

import com.matrix.crowdfunding.bean.TMenu;

import java.util.List;

/**
 * @Author yihaosun
 * @Date 2/4/2021 22:20
 */
public interface TMenuService {

    List<TMenu> listMenuAll(); // 组合父子关系

    List<TMenu> listMenuAllTree(); // 不用组合父子关系

    void saveTMenu(TMenu menu);

    TMenu getMenuById(Integer id);

    void updateTMenu(TMenu menu);

    void deleteTMenu(Integer id);

}
