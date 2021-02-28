package com.arino.mapper;

import com.arino.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenuMapper {
    List<Menu> getAllMenus();

    List<Menu> getChildrenById(int id);
}
