package com.example.dronemanagerhou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import java.util.List;

import com.example.dronemanagerhou.service.IRoleService;
import com.example.dronemanagerhou.entity.Role;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author drone
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/role")
        public class RoleController {
    
        @Resource
        private IRoleService roleService;

        //新增或者更新
        @PostMapping
        public Result save(@RequestBody Role role) {
                return Result.success(roleService.saveOrUpdate(role)) ;
                }
        //删除
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(roleService.removeById(id)) ;
                }

         //新增或者更新
        @PostMapping("/del/batch")
            public Result deleteBatch(@RequestBody List<Integer> ids) {
            return Result.success(roleService.removeByIds(ids)) ;
        }
        //查询所有数据
        @GetMapping
        public Result findAll() {
                return Result.success(roleService.list());
                }

        //根据ID查询
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(roleService.getById(id));
                }
        //分页查询
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
                return  Result.success(roleService.page(new Page<>(pageNum, pageSize),queryWrapper));
                }


        @PostMapping("/roleMenu/{roleId}")
        public Result roleMenu(@PathVariable Integer roleId,@RequestBody List<Integer> menuIds) {
                roleService.setRoleMenu(roleId,menuIds);
                return Result.success() ;
        }
        @GetMapping("/roleMenu/{roleId}")
        public Result getRoleMenu(@PathVariable Integer roleId) {
                return Result.success(roleService.getRoleMenu(roleId)) ;
        }
  }

