package com.gsy.ps.service;

import com.gsy.common.entity.Menu;
import com.gsy.ps.dao.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(Integer menuId) {
        return menuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public int insertSelective(Menu record) {
        return menuMapper.insertSelective(record);
    }

    @Override
    public Menu selectByPrimaryKey(Integer menuId) {
        return menuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }
}
