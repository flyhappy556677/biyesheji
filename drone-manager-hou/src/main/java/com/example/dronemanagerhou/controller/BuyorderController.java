package com.example.dronemanagerhou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

import com.example.dronemanagerhou.service.IBuyorderService;
import com.example.dronemanagerhou.entity.Buyorder;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author drone
 * @since 2024-04-03
 */
@RestController
@RequestMapping("/buyorder")
        public class BuyorderController {
    
        @Resource
        private IBuyorderService buyorderService;

        //新增或者更新
        @PostMapping
        public Result save(@RequestBody Buyorder buyorder) {
              int score=buyorder.getScore();
              buyorder.setUsescore(score);
              buyorder.setStatus(true);
            buyorder.setBuytime(LocalDateTime.now());
            return Result.success(buyorderService.saveorder(buyorder));
         }

         @PutMapping("/update")
            public Result update(@RequestBody Buyorder buyorder) {
                boolean success = buyorderService.updateBuyorder(buyorder);
                if (success) {
                    return Result.success("更新成功");
                } else {
                    return Result.error();
                }
        }

            @PutMapping("/newupdate/{id}")
            public Result newupdate(@PathVariable Integer id) {
                boolean success = buyorderService.newupdateBuyorder(id);
                if (success) {
                    return Result.success("更新成功");
                } else {
                    return Result.error();
                }
            }
        //删除
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
            return Result.success(buyorderService.removeById(id));
        }

         //新增或者更新
        @PostMapping("/del/batch")
            public Result deleteBatch(@RequestBody List<Integer> ids) {
            return Result.success( buyorderService.removeByIds(ids));
        }
        //查询所有数据
        @GetMapping
        public Result findAll() {
                return Result.success(buyorderService.list());
                }

        //根据ID查询
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(buyorderService.getNameById(id));
        }

        //分页查询
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String droneName
                                                                ) {
/*        QueryWrapper<Buyorder> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        buyorderService.findpage(new Page<>(pageNum, pageSize),queryWrapper);*/
        Page<Buyorder> page = buyorderService.findPageWithDrone(new Page<>(pageNum, pageSize),droneName);

        return Result.success(page);

        }

        @PutMapping("/updateStatus/{id}")
        public Result updateStatus(@PathVariable Integer id ) {
            return Result.success(buyorderService.updateStatus(id));
        }

}

