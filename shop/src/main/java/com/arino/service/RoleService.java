package com.arino.service;

import com.arino.mapper.PermissionMapper;
import com.arino.mapper.RoleMapper;
import com.arino.pojo.Permission;
import com.arino.pojo.Role;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    private final RoleMapper roleMapper;

    private final PermissionMapper permissionMapper;

    public RoleService(RoleMapper roleMapper, PermissionMapper permissionMapper) {
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    public List<Role> getRoles() {
        List<Role> roles = roleMapper.getRoles();
        for (Role role : roles) {
            addPermissions(role);
        }
        return roles;
    }

    private void addPermissions(Role role) {
        String[] permissions = role.getPermissions().split(",");
        Map<Integer, Permission> map = new HashMap<>();
        for (String permission : permissions) {
            if (permission.contentEquals(""))
                continue;
            Permission permission1 = permissionMapper.getPermissionById(Integer.parseInt(permission));
            permission1.init();
            map.put(permission1.getId(), permission1);
        }
        for (Map.Entry<Integer, Permission> entry : map.entrySet()) {
            Permission permission = entry.getValue();
            if (map.containsKey(permission.getPid())) {
                map.get(permission.getPid()).addChildren(permission);
            }
        }
        for (Permission permission : map.values()) {
            if (permission.getPid() == 0) {
                role.addPermission(permission);
            }
        }
    }

    public void addRole(String roleName, String roleDesc) {
        roleMapper.addRole(roleName, roleDesc);
    }

    public Role getRoleById(int id) {
        Role role = roleMapper.getRoleById(id);
        addPermissions(role);
        return role;
    }

    public Role getRoleByName(String name) {
        return roleMapper.getRoleByName(name);
    }

    public void updateRole(int id, String name, String desc) {
        roleMapper.updateRole(id, name, desc);
    }

    public void deleteRole(int id) {
        roleMapper.deleteRole(id);
    }

    public void deleteRoleRight(int roleId, int rightId) {
        Role role = getRoleById(roleId);
        Queue<Permission> queue = new LinkedList<>();
        for (Permission child : role.getChildren()) {
            queue.offer(child);
        }
        Permission temp = null;
        while (!queue.isEmpty()) {
            Permission permission = queue.poll();
            if (permission.getId() == rightId) {
                temp = permission;
                break;
            }
            List<Permission> children = permission.getChildren();
            if (children != null && !children.isEmpty()) {
                for (Permission child : children) {
                    queue.offer(child);
                }
            }
        }
        queue.clear();
        queue.add(temp);
        while (!queue.isEmpty()) {
            Permission permission = queue.poll();
            List<Permission> children = permission.getChildren();
            if (children != null && !children.isEmpty()) {
                for (Permission child : children) {
                    queue.offer(child);
                }
            }
            roleMapper.deleteRoleRight(roleId, permission.getId());
        }
    }

    public void allotRights(int roleId, String rights) {
        roleMapper.allotRights(roleId, rights);
    }
}
