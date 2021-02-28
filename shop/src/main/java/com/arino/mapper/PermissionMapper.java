package com.arino.mapper;

import com.arino.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PermissionMapper {
    List<Permission> getPermissions();

    Permission getPermissionById(int id);
}
