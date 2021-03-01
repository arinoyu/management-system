package com.arino.service;

import com.arino.mapper.PermissionMapper;
import com.arino.pojo.Permission;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionService {

    private final PermissionMapper permissionMapper;

    public PermissionService(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public List<Permission> getPermissionsTree(){
        Map<Integer, Permission> map = new HashMap<>();
        List<Permission> permissions = permissionMapper.getPermissions();
        for (Permission permission : permissions) {
            permission.init();
            map.put(permission.getId(),permission);
        }
        for (Map.Entry<Integer, Permission> entry : map.entrySet()) {
            Permission permission = entry.getValue();
            if (map.containsKey(permission.getPid())) {
                map.get(permission.getPid()).addChildren(permission);
            }
        }
        permissions.clear();
        for (Permission permission : map.values()) {
            if (permission.getPid() == 0) {
                permissions.add(permission);
            }
        }
        return permissions;
    }

    public List<Permission> getPermissionsList(){
        return permissionMapper.getPermissions();
    }
}
