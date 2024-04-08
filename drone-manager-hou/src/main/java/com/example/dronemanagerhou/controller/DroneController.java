package com.example.dronemanagerhou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.example.dronemanagerhou.service.IDroneService;
import com.example.dronemanagerhou.entity.Drone;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author drone
 * @since 2024-04-02
 */
@RestController
@RequestMapping("/drone")
        public class DroneController {
    
        @Resource
        private IDroneService droneService;

        //新增或者更新
        @PostMapping
        public Result save(@RequestBody Drone drone) {
            return Result.success(droneService.saveOrUpdate(drone));
         }
        //删除
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
            return Result.success(droneService.removeById(id));
        }

         //新增或者更新
        @PostMapping("/del/batch")
            public Result deleteBatch(@RequestBody List<Integer> ids) {
            return Result.success( droneService.removeByIds(ids));
        }
        //查询所有数据
        @GetMapping
        public Result findAll() {
                return Result.success(droneService.list());
                }

        //根据ID查询
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(droneService.getById(id));
        }

        //分页查询
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String name,
                               @RequestParam (defaultValue = "")String droneNo) {
        QueryWrapper<Drone> queryWrapper = new QueryWrapper<>();
                if(!"".equals(name)){
                        queryWrapper.like("name",name);
                }
                if(!"".equals(droneNo)){
                        queryWrapper.like("drone_no",droneNo);
                }
        queryWrapper.orderByDesc("id");
        return Result.success(droneService.page(new Page<>(pageNum, pageSize),queryWrapper));

        }
}

