package com.matrix.crowdfunding.component;

import com.matrix.crowdfunding.bean.TAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

/**
 * @Author: yihaosun
 * @Date: 2021/3/31 22:39
 */
public class TSecurityAdmin extends User {

    TAdmin admin;

    public TSecurityAdmin(TAdmin admin, Set<GrantedAuthority> authoritySet) {
//        super(admin.getLoginacct(), admin.getUserpswd(), authoritySet);
        super(admin.getLoginacct(), admin.getUserpswd(), true, true,true,true, authoritySet);
        this.admin = admin;
    }




}