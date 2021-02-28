package com.arino.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    private String roleName;
    private String roleDesc;
    @JsonIgnore
    private String permissions;
    private List<Permission> children = new LinkedList<>();

    public void addPermission(Permission permission) {
        children.add(permission);
    }
}
