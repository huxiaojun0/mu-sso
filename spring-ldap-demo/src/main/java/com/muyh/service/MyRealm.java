package com.muyh.service;

import com.muyh.dao.TPermissionMapper;
import com.muyh.dao.TRoleMapper;
import com.muyh.dao.TUserMapper;
import com.muyh.model.TPermission;
import com.muyh.model.TRole;
import com.muyh.model.TUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyRealm extends AuthorizingRealm{

    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private TRoleMapper tRoleMapper;
    @Autowired
    private TPermissionMapper tPermissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal(); //获取用户名
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        TUser tUser = tUserMapper.selectByUsername(username);
        Set<String> roles = new HashSet<String>();
        TRole tRole = tRoleMapper.selectByPrimaryKey(tUser.getId());
        roles.add(tRole.getRolename());
        authorizationInfo.setRoles(roles);
        Set<String> perms = new HashSet<String>();
        perms.add(tPermissionMapper.selectByRoleid(tRole.getId()).getPermissionname());
        authorizationInfo.setStringPermissions(perms);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String) authenticationToken.getPrincipal(); // 获取用户名
        TUser tUser = tUserMapper.selectByUsername(username);
        if (tUser != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                    tUser.getUsername(), tUser.getPassword(), "myrealm");
            return authcInfo;
        } else {
            return null;
        }
    }


}
