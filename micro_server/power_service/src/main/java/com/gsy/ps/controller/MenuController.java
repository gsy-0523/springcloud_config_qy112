package com.gsy.ps.controller;

import com.gsy.common.entity.Menu;
import com.gsy.ps.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @DeleteMapping("deleteByPrimaryKey")
    public int deleteByPrimaryKey(Integer menuId){
        return menuService.deleteByPrimaryKey(menuId);
    }

    @PostMapping("insertSelective")
    public int insertSelective(Menu record){
        return menuService.insertSelective(record);
    }

    @GetMapping("selectByPrimaryKey")
    public Menu selectByPrimaryKey(Integer menuId){
        return menuService.selectByPrimaryKey(menuId);
    }

    @PutMapping("updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Menu record){
        return menuService.updateByPrimaryKeySelective(record);
    }

    @GetMapping("selectAll")
    public List<Menu> selectAll(){
        return menuService.selectAll();
    }

}
