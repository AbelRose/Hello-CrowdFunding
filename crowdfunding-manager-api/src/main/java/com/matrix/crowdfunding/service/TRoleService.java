package com.matrix.crowdfunding.service;

import com.github.pagehelper.PageInfo;
import com.matrix.crowdfunding.bean.TRole;

import java.util.HashMap;
import java.util.List;

/**
 * @Author yihaosun
 * @Date 2/22/2021 22:20
 */
public interface TRoleService {
    PageInfo<TRole> listRolePage(HashMap<String, Object> paramMap);

    void saveTRole(TRole role);

    TRole getRoleById(Integer id);

    void updateTRole(TRole role);

    void deleteTRole(Integer id);

    List<TRole> listAllRole();

    List<Integer> getRoleIdByAdminId(String id);

    void saveAdminAndRoleRelationship(Integer[] roleId, Integer adminId);

    void deleteAdminAndRoleRelationship(Integer[] roleId, Integer adminId);
}
