package com.matrix.crowdfunding.service;

import com.github.pagehelper.PageInfo;
import com.matrix.crowdfunding.bean.TRole;
import com.matrix.crowdfunding.mapper.TRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public PageInfo<TRole> listRolePage(HashMap<String, Object> paramMap) {
        List<TRole> list = roleMapper.selectByExample(null);
        PageInfo<TRole> page = new PageInfo<>(list, 5);
        return page;
    }
}
