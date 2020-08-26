package com.gsy.ps.service;

import com.gsy.common.entity.Menu;

import java.util.List;

public interface MenuService {
    int deleteByPrimaryKey(Integer menuId);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    List<Menu> selectAll();
}
