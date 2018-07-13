package com.baizhi.cmfz.util;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Permission;
import com.baizhi.cmfz.entity.Role;
import com.baizhi.cmfz.service.ManagerService;
import com.google.common.io.ByteSource;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.provider.MD5;

import java.util.List;
import java.util.UUID;

/**
 * @Description AuthorizingRealm的实现类，自定义用户信息数据源
 * @Author weizimo
 * @Time 2018/7/11 21:43.
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    ManagerService ms;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<Role> roleList = ms.queryRoleByUserName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role: roleList
             ) {
            info.addRole(role.getRoleTag());
        }
        List<Permission> permissions = ms.queryPermissionByUserName(username);
        for (Permission per: permissions
             ) {
            info.addStringPermission(per.getPermissionTag());
        }
        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        String username = usernamePasswordToken.getUsername();
        String pwd = String.valueOf(usernamePasswordToken.getPassword());
        Manager mgr = ms.queryMgr(username);
        if(mgr!=null){
            return new SimpleAuthenticationInfo(mgr.getMgrName(),
                    mgr.getMgrPwd(),
                    org.apache.shiro.util.ByteSource.Util.bytes(mgr.getMgrSalt()),
                    UUID.randomUUID().toString());
        }
        return null;
    }
}
