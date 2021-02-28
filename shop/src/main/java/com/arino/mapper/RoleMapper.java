package com.arino.mapper;

import com.arino.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleMapper {
    Role getRoleById(int id);

    Role getRoleByName(String name);

    List<Role> getRoles();

    void addRole(@Param("roleName") String roleName, @Param("roleDesc") String roleDesc);

    void updateRole(@Param("id") int id, @Param("name") String name, @Param("desc") String desc);

    void deleteRole(int id);

    void deleteRoleRight(@Param("roleId") int roleId, @Param("rightId") int rightId);

    void allotRights(@Param("roleId") int roleId, @Param("rights") String rights);
}
