package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.ManagerDao;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Permission;
import com.baizhi.cmfz.entity.Role;
import com.baizhi.cmfz.service.ManagerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/4 15:52.
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{

     @Autowired
    private ManagerDao mg;
    /**
     * @Description 登录
     * @Author      weizimo
     * @Time        2018-07-04
     * @Param       mgrName：管理员用户名 mgrPwd：管理员密码
     * @Exception  无
     */
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Manager login(String mgrName, String mgrPwd) {
        Manager mgr = null;
        mgr = mg.findManager(mgrName);

        if(mgr!=null){
            mgrPwd= DigestUtils.md5Hex(mgrPwd+mgr.getMgrSalt());
            if(mgr.getMgrName().equals(mgrName)&& mgr.getMgrPwd().equals(mgrPwd)){
                return mgr;
            }
        }
        return null;
    }

    public Manager queryMgr(String mgrName) {
        Manager mgr = mg.findManager(mgrName);
        return mgr;
    }

    public List<Role> queryRoleByUserName(String mgrName) {
        List<Role> roles = mg.findRolesByUserName(mgrName);
        return roles;
    }

    public List<Permission> queryPermissionByUserName(String mgrName) {
        List<Permission> permissions = mg.findPermissionByUserName(mgrName);
        return permissions;
    }

    public ManagerDao getMg() {
        return mg;
    }

    public void setMg(ManagerDao mg) {
        this.mg = mg;
    }
}
