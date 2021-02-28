package com.arino.controller;

import cn.hutool.core.map.MapUtil;
import com.arino.common.Result;
import com.arino.pojo.Permission;
import com.arino.service.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/rights/list")
    public Result rightsList() {
        List<Permission> permissions = permissionService.getPermissionsList();
        List<Map<Object, Object>> list = new LinkedList<>();
        for (Permission permission : permissions) {
            Map<Object, Object> map = MapUtil.builder()
                    .put("id", permission.getId())
                    .put("authName", permission.getAuthName())
                    .put("level", permission.getLevel())
                    .put("pid", permission.getPid())
                    .put("path", permission.getPath())
                    .map();
            list.add(new HashMap<>(map));
        }
        return Result.success("获取权限列表成功", list);
    }

    @GetMapping("/rights/tree")
    public Result rightTree() {
        List<Permission> tree = permissionService.getPermissionsTree();
        return Result.success("获取权限列表成功", tree);
    }
}
