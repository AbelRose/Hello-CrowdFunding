package com.matrix.crowdfunding.service;

import com.github.pagehelper.PageInfo;
import com.matrix.crowdfunding.bean.TAdmin;
import com.matrix.crowdfunding.bean.TAdminExample;
import com.matrix.crowdfunding.exception.LoginException;
import com.matrix.crowdfunding.mapper.TAdminMapper;
import com.matrix.crowdfunding.util.AppDateUtils;
import com.matrix.crowdfunding.util.Const;
import com.matrix.crowdfunding.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author yihaosun
 * @Date 2/2/2021 19:49
 */
@Service  // 纳入到IOC容器中
public class TAdminServiceImpl implements TAdminService {
    @Autowired
    private TAdminMapper adminMapper;

    @Override
    public TAdmin getTAdminByLogin(Map<String, Object> paramMap) {
        //1. 密码加密

        //2. 查询用户
        String loginacct  = (String)paramMap.get("loginacct");
        String userpswd  = (String)paramMap.get("userpswd");

        TAdminExample example = new TAdminExample();  // 需要向里面添加条件
        example.createCriteria().andLoginacctEqualTo(loginacct);  // 根据用户名查找
        List<TAdmin> list = adminMapper.selectByExample(example);  // 查询所有
//      业务思路不是很流畅 换一种顺序的
          //3. 判断用户是否为null
//        if (!CollectionUtils.isEmpty(list) && list.size() == 1) {
//            TAdmin admin = list.get(0);
//            //4. 判断密码
//            if (admin.getUserpswd().equals(userpswd)) {
//                //5, 返回结果
//                return admin;
//            } else {
//                throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
//            }
//        } else {
//            throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
//        }

        // 顺序结构 逻辑更清晰
        //3. 判断用户是否为null
        if(CollectionUtils.isEmpty(list) || list.size() == 0) {
            throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
        }

        //4. 判断密码是否一致
        TAdmin admin = list.get(0);
        if (!admin.getUserpswd().equals(MD5Util.digest(userpswd))) {
           throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
        }

        //5, 返回结果
        return admin;
    }

    @Override
    public PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap) {
        TAdminExample example = new TAdminExample();
        // 为了能够使新增的数据在第一页显示 于是用到了倒叙排序
//        example.setOrderByClause("createtime desc");
        List<TAdmin> list =  adminMapper.selectByExample(example);  // null代表所有 查询所有
        PageInfo<TAdmin> page = new PageInfo<>(list, 5);  // navigatePages 是下面有几个导航页的数量 默认是8
        // 给list属性赋值的过程就是封装
        return page;
    }

    @Override
    public void saveTAdmin(TAdmin admin) {
        // 这两个属性是自己创建出来的 可以不用非得在SQL语句中 最后这五个值都会在数据库表中
        admin.setUserpswd(MD5Util.digest(Const.DEFAULT_USERPSWD));
        admin.setCreatetime(AppDateUtils.getFormatTime());
        adminMapper.insertSelective(admin);  // 动态sql 有选择性的保存 有字段的就保存没有就不保存(比如密码和创建时间的属性 在数据库中是有字段的但是可以先不写到sql语句中 可以在外面赋值)
    }

    @Override
    public TAdmin getTAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateTAdmin(TAdmin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);  // 有选择的更新即可以只更新某几个字段 比如password和createtime就不需要更新
    }
}
