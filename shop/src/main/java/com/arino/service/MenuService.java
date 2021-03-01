package com.arino.service;

import com.arino.mapper.MenuMapper;
import com.arino.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<Menu> getAllMenus() {
        List<Menu> menus = menuMapper.getAllMenus();
        List<Menu> res = new LinkedList<>();
        for (Menu menu : menus) {
            int id = menu.getId();
            if (id == 103 || id == 110 || id == 111 || id == 112 || id == 125) {
                res.add(menu);
            }
        }
        return res;
    }
}
