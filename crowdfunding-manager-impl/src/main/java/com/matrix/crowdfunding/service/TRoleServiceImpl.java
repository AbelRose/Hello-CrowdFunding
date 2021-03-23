package com.matrix.crowdfunding.service;

import com.github.pagehelper.PageInfo;
import com.matrix.crowdfunding.bean.TRole;
import com.matrix.crowdfunding.bean.TRoleExample;
import com.matrix.crowdfunding.mapper.TAdminRoleMapper;
import com.matrix.crowdfunding.mapper.TRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @Author yihaosun
 * @Date 2/22/2021 22:21
 */
@Service
public class TRoleServiceImpl implements TRoleService {

    @Autowired
    private TRoleMapper roleMapper;

    @Autowired
    private TAdminRoleMapper adminRoleMapper;

    @Override
    public PageInfo<TRole> listRolePage(HashMap<String, Object> paramMap) {

        String condition = (String) paramMap.get("condition");

        List<TRole> list = null;

        if (StringUtils.isEmpty(condition)) {
            list = roleMapper.selectByExample(null);  // 如果是空 查询所有
        } else { // 如果有条件
            TRoleExample example = new TRoleExample();
            example.createCriteria().andNameLike("%" + condition + "%");
            list = roleMapper.selectByExample(example);
        }
        PageInfo<TRole> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public void saveTRole(TRole role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public TRole getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateTRole(TRole role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteTRole(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TRole> listAllRole() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public List<Integer> getRoleIdByAdminId(String id) {
        return adminRoleMapper.getRoleIdByAdminId(id);
    }

    @Override
    public void saveAdminAndRoleRelationship(Integer[] roleId, Integer adminId) {
        adminRoleMapper.saveAdminAndRoleRelationship(roleId, adminId);
    }
}
