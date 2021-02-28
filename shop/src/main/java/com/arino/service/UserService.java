package com.arino.service;

import com.arino.mapper.UserMapper;
import com.arino.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 1.查询用户
        User user = userMapper.getUserByName(s);
        if (user == null) {
            // 找不到用户抛出异常
            throw new UsernameNotFoundException("用户名不存在!");
        }
        String role = user.getRoleName();
        List<String> roles = new LinkedList<>();
        roles.add(role);
        user.setRoles(roles);
        return user;
    }

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public List<User> getUsersLimit(int start, int end, String query) {
        return userMapper.getUsersLimit(start, end, query);
    }

    public int totalNumber(String query) {
        return userMapper.totalNumber(query);
    }

    public void updateUserById(int id, int state) {
        userMapper.updateUserById(id, state);
    }

    public void addUser(String username, String password, String email, String mobile) {
        // 先加密密码，再存储到数据库
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        // 默认的roleId是超级管理员的roleId
        userMapper.addUser(1, username, encryptedPassword, email, mobile);
    }

    public void editUser(int id, String email, String mobile) {
        userMapper.editUser(id, email, mobile);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    public void allotUserRole(int id, int rid) {
        userMapper.allotUserRole(id, rid);
    }

    public void updateUserRole(int rid) {
        userMapper.updateUserRole(rid);
    }
}
