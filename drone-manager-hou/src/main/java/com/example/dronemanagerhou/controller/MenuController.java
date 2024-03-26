package com.example.dronemanagerhou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.common.Constants;
import com.example.dronemanagerhou.common.Result;
import com.example.dronemanagerhou.entity.Dict;
import com.example.dronemanagerhou.mapper.DictMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.stream.Collectors;

import com.example.dronemanagerhou.service.IMenuService;
import com.example.dronemanagerhou.entity.Menu;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author drone
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/menu")
        public class MenuController {
    
        @Resource
        private IMenuService menuService;

        @Resource
        private DictMapper dictMapper;

        //新增或者更新
        @PostMapping
        public Result save(@RequestBody Menu menu) {
                return Result.success(menuService.saveOrUpdate(menu));
        }
        //删除
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(menuService.removeById(id));
                }

         //新增或者更新
        @PostMapping("/del/batch")
            public Result deleteBatch(@RequestBody List<Integer> ids) {
            return Result.success(menuService.removeByIds(ids));
        }
        //查询所有数据
        @GetMapping("/ids")
        public Result findAlls() {
                return Result.success(menuService.list().stream().map(Menu::getId));
        }
        //查询所有数据
        @GetMapping
        public Result findAll(@RequestParam(defaultValue = "") String name) {
                return Result.success(menuService.findMenus(name));
        }

        //根据ID查询
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(menuService.getById(id));
                }
        //分页查询
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
                return Result.success(menuService.page(new Page<>(pageNum, pageSize),queryWrapper));
        }
        //根据ID查询
        @GetMapping("/icons")
        public Result getIcon() {
                QueryWrapper<Dict> queryWrapper =new QueryWrapper<>();
                queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
                return Result.success(dictMapper.selectList(queryWrapper));
        }

}

