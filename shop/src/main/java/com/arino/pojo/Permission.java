package com.arino.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private int id;
    private int pid;
    private String authName;
    private String path;
    private String level;
    private List<Permission> children = new LinkedList<>();

    public void addChildren(Permission permission) {
        children.add(permission);
    }

    public void init() {
        if (level.contentEquals("2"))
            children = null;
    }
}
