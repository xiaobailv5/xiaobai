package com.example.lv.dao.mapper.base;

import com.example.lv.dao.entity.base.Permission;

import java.util.List;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 权限PermissionDao
 * @date 2023/6/22 12:44:56
 */
public interface PermissionDao {


    List<Permission> queryPermission(Integer roleId);
}
