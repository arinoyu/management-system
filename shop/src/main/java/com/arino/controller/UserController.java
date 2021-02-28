package com.arino.controller;

import cn.hutool.core.map.MapUtil;
import com.arino.common.Result;
import com.arino.pojo.User;
import com.arino.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Result users(@RequestParam("pagenum") int pageNum, @RequestParam("pagesize") int pageSize, @RequestParam(value = "query", defaultValue = "") String query) {
        List<User> userList = userService.getUsersLimit((pageNum - 1) * pageSize, pageSize, query);
        List<Map<Object, Object>> users = new LinkedList<>();
        for (User user : userList) {
            Map<Object, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("role_name", user.getRoleName());
            userMap.put("username", user.getUsername());
            userMap.put("create_time", Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(user.getCreateTime())));
            userMap.put("mobile", user.getMobile());
            userMap.put("email", user.getEmail());
            userMap.put("mg_state", user.isMgState());
            users.add(new HashMap<>(userMap));
        }
        Map<Object, Object> dataMap = new HashMap<>();
        dataMap.put("total", userService.totalNumber(query));
        dataMap.put("pagenum", pageNum);
        dataMap.put("users", users);
        return Result.success("获取管理员列表成功", dataMap);
    }

    @PutMapping("/users/{id}/state/{type}")
    public Result updateState(@PathVariable("id") int id, @PathVariable("type") boolean type) {
        userService.updateUserById(id, type ? 1 : 0);
        User user = userService.getUserById(id);
        if (user == null) {
            return Result.fail("管理员ID不存在", 400);
        }
        Map<Object, Object> dataMap = new HashMap<>();
        dataMap.put("id", user.getId());
        dataMap.put("rid", user.getRid());
        dataMap.put("username", user.getUsername());
        dataMap.put("mobile", user.getMobile());
        dataMap.put("email", user.getEmail());
        dataMap.put("mg_state", user.isMgState() ? 1 : 0);
        return Result.success("设置状态成功", dataMap);
    }

    @PostMapping(value = "/users")
    public Result addUser(@RequestBody Map<String, String> map) {
        userService.addUser(map.get("username"), map.get("password"), map.get("email"), map.get("mobile"));
        log.info("用户:{} 已被添加", map.get("username"));
        User user = userService.getUserByName(map.get("username"));
        return Result.success("创建成功", mapUser(user), 201);
    }

    @PutMapping("/users/{id}")
    public Result editUser(@PathVariable("id") int id, @RequestBody Map<String, String> map) {
        userService.editUser(id, map.get("email"), map.get("mobile"));
        User user = userService.getUserById(id);
        return Result.success("更新成功", mapUser(user));
    }

    @GetMapping("/users/{id}")
    public Result getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return Result.success("获取成功", mapUser(user));
    }

    @DeleteMapping("/users/{id}")
    public Result deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return Result.success("删除成功", null);
    }

    @PutMapping("/users/{id}/role")
    public Result allotUserRole(@PathVariable("id") int id, @RequestBody Map<String, Integer> map) {
        userService.allotUserRole(id, map.get("rid"));
        User user = userService.getUserById(id);
        Map<Object, Object> dataMap = MapUtil.builder()
                .put("id", user.getId())
                .put("rid", user.getRid())
                .put("username", user.getUsername())
                .put("mobile", user.getMobile())
                .put("email", user.getEmail())
                .map();
        return Result.success("设置角色成功", dataMap);
    }

    private Map<Object, Object> mapUser(User user) {
        return MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("role_id", user.getRid())
                .put("mobile", user.getMobile())
                .put("email", user.getEmail())
                .put("create_time", Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(user.getCreateTime())))
                .map();
    }

}
