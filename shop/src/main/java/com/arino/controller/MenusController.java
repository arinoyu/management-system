package com.arino.controller;

import com.arino.common.Result;
import com.arino.pojo.Menu;
import com.arino.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;

@Controller
public class MenusController {

    private final MenuService menuService;

    @Autowired
    public MenusController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ResponseBody
    @GetMapping("/menus")
    public Result menus() {
        List<Menu> menus = menuService.getAllMenus();
        menus.sort(Comparator.comparingInt(Menu::getOrder));
        return Result.success("获取菜单列表成功", menus);
    }
}
