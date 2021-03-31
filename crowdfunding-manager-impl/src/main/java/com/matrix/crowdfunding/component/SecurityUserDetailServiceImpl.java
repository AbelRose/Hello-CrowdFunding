package com.matrix.crowdfunding.component;

import com.matrix.crowdfunding.bean.TAdmin;
import com.matrix.crowdfunding.bean.TAdminExample;
import com.matrix.crowdfunding.bean.TPermission;
import com.matrix.crowdfunding.bean.TRole;
import com.matrix.crowdfunding.mapper.TAdminMapper;
import com.matrix.crowdfunding.mapper.TPermissionMapper;
import com.matrix.crowdfunding.mapper.TRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 查询用户对象
        TAdminExample adminExample = new TAdminExample();
        adminExample.createCriteria().andLoginacctEqualTo(username);
        List<TAdmin> list = adminMapper.selectByExample(adminExample);
        if (list != null && list.size() == 1) {
            TAdmin admin = list.get(0);
            Integer adminId = admin.getId();
            // 2，查询角色集合
            List<TRole> roleList = roleMapper.listRoleByAdminId(adminId);
            // 3. 查询权限集合
            List<TPermission> permissionList = permissionMapper.listPermissionByAdminId(adminId);
            // 4. 构建用户所有的权限集合=》ROLE_角色+权限
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (TRole role : roleList  ) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            }

            for (TPermission permission : permissionList ) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
//            return new User(admin.getLoginacct(), admin.getUserpswd(), null);
            return new TSecurityAdmin(admin, authorities);
        }else {
            return null;
        }
    }
}
