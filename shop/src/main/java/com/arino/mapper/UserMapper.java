package com.arino.mapper;

import com.arino.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    User getUserByName(String name);

    List<User> getUsersLimit(@Param("start") int start, @Param("end") int end, @Param("query") String query);

    int totalNumber(@Param("query") String query);

    void updateUserById(@Param("id") int id, @Param("state") int state);

    User getUserById(int id);

    void addUser(@Param("rid") int rid, @Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("mobile") String mobile);

    void editUser(@Param("id") int id, @Param("email") String email, @Param("mobile") String mobile);

    void deleteUser(@Param("id") int id);

    void allotUserRole(@Param("id") int id, @Param("rid") int rid);

    void updateUserRole(int rid);
}
