package com.arino.controller;

import cn.hutool.core.map.MapUtil;
import com.arino.common.Result;
import com.arino.pojo.Role;
import com.arino.service.RoleService;
import com.arino.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RoleController {

    private final RoleService roleService;

    private final UserService userService;

    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/roles")
    public Result roles() {
        return Result.success("获取成功", roleService.getRoles());
    }

    @PostMapping("/roles")
    public Result addRole(@RequestBody Map<String, String> map) {
        roleService.addRole(map.get("roleName"), map.get("roleDesc"));
        Role role = roleService.getRoleByName(map.get("roleName"));
        return Result.success("创建成功", convert(role), 201);
    }

    @PutMapping("/roles/{id}")
    public Result editRole(@PathVariable int id, @RequestBody Map<String, String> map) {
        roleService.updateRole(id, map.get("roleName"), map.get("roleDesc"));
        Role role = roleService.getRoleById(id);
        return Result.success("获取成功", convert(role));
    }

    @GetMapping("/roles/{id}")
    public Result getRole(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        return Result.success("获取成功", convert(role));
    }

    @DeleteMapping("/roles/{id}")
    public Result deleteRole(@PathVariable int id) {
        if (id == 1) {
            return Result.fail("超级管理员不能被删除", 400);
        }
        userService.updateUserRole(id);
        roleService.deleteRole(id);
        return Result.success("删除成功", null);
    }

    @DeleteMapping("/roles/{roleId}/rights/{rightId}")
    public Result deleteRight(@PathVariable("roleId") int roleId, @PathVariable("rightId") int rightId) {
        roleService.deleteRoleRight(roleId, rightId);
        Role role = roleService.getRoleById(roleId);
        return Result.success("取消权限成功", role.getChildren());
    }

    @PostMapping("/roles/{roleId}/rights")
    public Result allotRights(@PathVariable("roleId") int roleId, @RequestBody Map<String, String> map) {
        roleService.allotRights(roleId, map.get("rids"));
        return Result.success("更新成功", null);
    }

    private Map<Object, Object> convert(Role role) {
        return MapUtil.builder()
                .put("roleId", role.getId())
                .put("roleName", role.getRoleName())
                .put("roleDesc", role.getRoleDesc())
                .map();
    }
}
