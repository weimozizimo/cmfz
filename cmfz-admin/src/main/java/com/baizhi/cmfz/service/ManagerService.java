package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Manager;

/**
 * @Description 管理员业务层的接口类
 * @Author  weizimo
 * @Time    2018/7/4 15:49
 */
public interface ManagerService {
    /**
     *
     * @Description 管理员登录操作
     * @Author  weizimo
     * @Time    2018/7/4 15:49
     * @Param   mgrName:管理员用户名
     *           mgrPwd:管理员密码
     * @Exception   无
     */
    public Manager login(String mgrName, String mgrPwd);

}
