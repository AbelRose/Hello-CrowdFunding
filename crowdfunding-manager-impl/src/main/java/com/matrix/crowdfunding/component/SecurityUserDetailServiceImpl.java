package com.matrix.crowdfunding.component;

import com.matrix.crowdfunding.bean.TPermission;
import com.matrix.crowdfunding.mapper.TAdminMapper;
import com.matrix.crowdfunding.mapper.TPermissionMapper;
import com.matrix.crowdfunding.mapper.TRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author: yihaosun
 * @Date: 2021/3/30 00:27
 */
@Component
public class SecurityUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private TAdminMapper adminMapper; // 查用户

    @Autowired
    private TRoleMapper roleMapper; // 查角色

    @Autowired
    private TPermissionMapper permissionMapper; // 查权限

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
