package com.example.dronemanagerhou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import java.util.List;

import com.example.dronemanagerhou.service.IOperateService;
import com.example.dronemanagerhou.entity.Operate;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author drone
 * @since 2024-04-07
 */
@RestController
@RequestMapping("/operate")
        public class OperateController {
    
        @Resource
        private IOperateService operateService;

        //新增或者更新
        @PostMapping
        public Result save(@RequestBody Operate operate) {
            return Result.success(operateService.save(operate));
         }
        //删除
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
            return Result.success(operateService.removeById(id));
        }

         //新增或者更新
        @PostMapping("/del/batch")
            public Result deleteBatch(@RequestBody List<Integer> ids) {
            return Result.success( operateService.removeByIds(ids));
        }
        //查询所有数据
        @GetMapping
        public Result findAll() {
                return Result.success(operateService.list());
                }

        //根据ID查询
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(operateService.getById(id));
        }

        //分页查询
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize) {
        QueryWrapper<Operate> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(operateService.page(new Page<>(pageNum, pageSize),queryWrapper));

        }

         }

