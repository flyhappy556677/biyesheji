package com.example.dronemanagerhou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.common.Result;
import com.example.dronemanagerhou.entity.Buyorder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import java.util.List;

import com.example.dronemanagerhou.service.IMaintenanceService;
import com.example.dronemanagerhou.entity.Maintenance;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author drone
 * @since 2024-04-07
 */
@RestController
@RequestMapping("/maintenance")
        public class MaintenanceController {
    
        @Resource
        private IMaintenanceService maintenanceService;

        //新增或者更新
        @PostMapping
        public Result save(@RequestBody Maintenance maintenance) {
            return Result.success(maintenanceService.saveOrUpdate(maintenance));
         }
        //删除
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
            return Result.success(maintenanceService.removeById(id));
        }

         //新增或者更新
        @PostMapping("/del/batch")
            public Result deleteBatch(@RequestBody List<Integer> ids) {
            return Result.success( maintenanceService.removeByIds(ids));
        }
        //查询所有数据
        @GetMapping
        public Result findAll() {
                return Result.success(maintenanceService.list());
                }

        //根据ID查询
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(maintenanceService.getNameById(id));
        }
        @PutMapping("/update")
        public Result update(@RequestBody Maintenance maintenance) {
            return Result.success(maintenanceService.updateBuyorder(maintenance));
        }

        //分页查询
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
                Page<Maintenance> page = maintenanceService.findPageWith(new Page<>(pageNum, pageSize));

                return Result.success(page);
        }



         }

