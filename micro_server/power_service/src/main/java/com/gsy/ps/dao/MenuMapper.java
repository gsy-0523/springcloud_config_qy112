package com.gsy.ps.dao;

import com.gsy.common.entity.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    List<Menu>selectAll();
}