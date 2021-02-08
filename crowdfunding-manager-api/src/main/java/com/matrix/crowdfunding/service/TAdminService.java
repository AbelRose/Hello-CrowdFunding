package com.matrix.crowdfunding.service;

import com.github.pagehelper.PageInfo;
import com.matrix.crowdfunding.bean.TAdmin;

import java.util.Map;

/**
 * @Author yihaosun
 * @Date 2/2/2021 19:47
 */
public interface TAdminService {
    TAdmin getTAdminByLogin(Map<String, Object> paramMap);
    PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap);
    void saveTAdmin(TAdmin admin);
    TAdmin getTAdminById(Integer id);
    void updateTAdmin(TAdmin admin);
}
