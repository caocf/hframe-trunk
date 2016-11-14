package com.hframework.base.bean;

import com.google.common.collect.Lists;
import com.hframework.common.ext.CollectionUtils;
import com.hframework.common.ext.Fetcher;

import java.util.*;

/**
 * Created by zhangquanhong on 2016/11/2.
 */
public class AuthContext {

    private AuthUser user;

    private AuthManager authManager = new AuthManager();

    private AuthDataUnitRelManager authDataUnitRelManager = new AuthDataUnitRelManager();

    private AuthFunctionManager authFunctionManager = new AuthFunctionManager();


    public class AuthManager extends HashMap<Long, List<AuthDataUnit>> {

        private Class authDataClass;
        private Class authFunctionClass;


        public void add(Long dataUnitId, Long functionId) {
            if(!this.containsKey(functionId)) {
                this.put(functionId, new ArrayList<AuthDataUnit>());
            }

            Iterator<AuthDataUnit> iterator = this.get(functionId).iterator();
            boolean isChild = false;
            while (iterator.hasNext()) {
                AuthDataUnit next = iterator.next();
                if(authDataUnitRelManager.isChild(next.dataUnitId, dataUnitId)){
                    iterator.remove();
                }

                if(authDataUnitRelManager.isParent(next.dataUnitId, dataUnitId)){
                    return;//添加的节点对应的父节点已经在授权数据列表中，直接返回
                }
            }

            this.get(functionId).add(AuthDataUnit.valueOf(dataUnitId));
        }

        public List<Long> getDataUnitIds(Long functionId) {
            Set<Long> dataUnitIds = new HashSet<Long>();
            if(this.containsKey(functionId)) {
                List<AuthDataUnit> authDataUnits = this.get(functionId);
                for (AuthDataUnit authDataUnit : authDataUnits) {
                    dataUnitIds.add(authDataUnit.dataUnitId);
                    dataUnitIds.addAll(authDataUnitRelManager.getChildren(authDataUnit.dataUnitId));

                }
                return Lists.newArrayList(dataUnitIds);
            }
            return Lists.newArrayList();
        }

        public Class getAuthDataClass() {
            return authDataClass;
        }

        public void setAuthDataClass(Class authDataClass) {
            this.authDataClass = authDataClass;
        }

        public Class getAuthFunctionClass() {
            return authFunctionClass;
        }

        public void setAuthFunctionClass(Class authFunctionClass) {
            this.authFunctionClass = authFunctionClass;
        }
    }

    public class AuthDataUnitRelManager extends HashMap<Long, Set<Long>> {

        private Map<Long, Set<Long>> subMap = new HashMap<Long, Set<Long>>();

        public void add(Long subAuthDataUnitId, Long parentAuthDataUnitId) {
            if(!this.containsKey(subAuthDataUnitId)) {
                this.put(subAuthDataUnitId, new HashSet<Long>());
            }
            this.get(subAuthDataUnitId).add(parentAuthDataUnitId);
        }

        public void addAll(Long subAuthDataUnitId, Set<Long> parentAuthDataUnitIds) {
            if(!this.containsKey(subAuthDataUnitId)) {
                this.put(subAuthDataUnitId, new HashSet<Long>());
            }
            this.get(subAuthDataUnitId).addAll(parentAuthDataUnitIds);
        }

        public boolean isChild(Long currentDataUnitId, Long targetDataUnitId) {
            return this.get(currentDataUnitId).contains(targetDataUnitId);
        }

        public boolean isParent(Long currentDataUnitId, Long targetDataUnitId) {
            return this.get(targetDataUnitId).contains(currentDataUnitId);
        }

        public Set<Long> getChildren(Long parentAuthDataUnitId) {
            Set<Long> children = new HashSet<Long>();

            for (Map.Entry<Long, Set<Long>> longSetEntry : this.entrySet()) {
                if(longSetEntry.getValue().contains(parentAuthDataUnitId)) {
                    children.add(longSetEntry.getKey());
                }
            }
            return children;
        }

    }

    public class AuthFunctionManager extends HashMap<String, Long> {
    }

    public static class AuthUser{
        public Class userClass;
        public Object userObject;
        public Long userId;

        public static AuthUser valueOf(Object object) {
            AuthUser authUser = new AuthUser();
            authUser.userClass = object.getClass();
            authUser.userObject = object;
            return  authUser;
        }
    }

    public static class AuthDataUnit{
        public Class dataUnitClass;
        public Object dataUnitObject;
        public Long dataUnitId;

        public static AuthDataUnit valueOf(Long authDataId) {
            AuthDataUnit authDataUnit = new AuthDataUnit();
            authDataUnit.dataUnitId = authDataId;
            return  authDataUnit;
        }

        public static AuthDataUnit valueOf(Object object) {
            AuthDataUnit authDataUnit = new AuthDataUnit();
            authDataUnit.dataUnitClass = object.getClass();
            authDataUnit.dataUnitObject = object;
            return  authDataUnit;
        }
    }

    public static class AuthDataUnitRel{
        public Class dataUnitClass;
        public Object dataUnitObject;
        public Long dataUnitId;

        public static AuthDataUnit valueOf(Object object) {
            AuthDataUnit authDataUnit = new AuthDataUnit();
            authDataUnit.dataUnitClass = object.getClass();
            authDataUnit.dataUnitObject = object;
            return  authDataUnit;
        }
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = AuthUser.valueOf(user);
    }

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

    public AuthDataUnitRelManager getAuthDataUnitRelManager() {
        return authDataUnitRelManager;
    }

    public void setAuthDataUnitRelManager(AuthDataUnitRelManager authDataUnitRelManager) {
        this.authDataUnitRelManager = authDataUnitRelManager;
    }

    public AuthFunctionManager getAuthFunctionManager() {
        return authFunctionManager;
    }

    public void setAuthFunctionManager(AuthFunctionManager authFunctionManager) {
        this.authFunctionManager = authFunctionManager;
    }
}
