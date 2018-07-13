package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Permission;
import com.baizhi.cmfz.entity.Role;

import java.util.List;

/**
 * @Description dao层对Manager进行数据库操作
 * @Author weizimo
 *@Time 2018/7/4.
 */
public interface ManagerDao {
    /**
     * @Description 根据管理员用户名检索管理员
     * @Author      weizimo
     * @Time        2018/7/4
     * @Param       mgrName:管理员的用户名
     * @Exception   无
     */
    public Manager findManager(String mgrName);

    public List<Role> findRolesByUserName(String mgrName);

    public List<Permission> findPermissionByUserName(String mgrName);
}
