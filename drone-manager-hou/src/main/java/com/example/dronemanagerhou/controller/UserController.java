package com.example.dronemanagerhou.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dronemanagerhou.common.Constants;
import com.example.dronemanagerhou.common.Result;
import com.example.dronemanagerhou.controller.dto.UserDTO;
import com.example.dronemanagerhou.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.example.dronemanagerhou.service.IUserService;
import com.example.dronemanagerhou.entity.User;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author drone
 * @since 2024-03-14
 */
@RestController
@RequestMapping("/user")
        public class UserController {
    
        @Resource
        private IUserService userService;

        @PostMapping("/login")
        public Result login(@RequestBody UserDTO userDTO){
            String username=userDTO.getUsername();
            String password=userDTO.getPassword();
            if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
                return Result.error(Constants.CODE_400,"参数错误");
            }
            UserDTO dto=userService.login(userDTO);
            return Result.success(dto);
        }

        //新增或者更新
        @PostMapping
        public Result save(@RequestBody User user) {
                return Result.success(userService.saveOrUpdate(user));
                }
        //删除
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return  Result.success(userService.removeById(id));
                }

         //新增或者更新
        @PostMapping("/del/batch")
            public Result deleteBatch(@RequestBody List<Integer> ids) {
            return Result.success(userService.removeByIds(ids));
        }

        @PostMapping("/register")
        public Result register(@RequestBody UserDTO userDTO) {
            String username=userDTO.getUsername();
            String password=userDTO.getPassword();
            if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
                return Result.error(Constants.CODE_400,"参数错误");
            }
            return Result.success(userService.register(userDTO));
        }
        //查询所有数据
        @GetMapping
        public Result findAll()  {
            return Result.success(userService.list());
        }

        @GetMapping("/username/{username}")
        public Result findOne(@PathVariable String username) {
           QueryWrapper<User> queryWrapper=new QueryWrapper<>();
           queryWrapper.eq("username",username);
           return Result.success(userService.getOne(queryWrapper));
        }

        //根据ID查询
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(userService.getById(id));
        }
        //分页查询
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String username,
                                   @RequestParam (defaultValue = "")String nickname,
                                   @RequestParam (defaultValue = "")String address) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
            if(!"".equals(username)){
                queryWrapper.like("username",username);
            }
            if(!"".equals(nickname)){
                queryWrapper.like("nickname",nickname);
            }
            if(!"".equals(address)){
                queryWrapper.like("address",address);
            }
            //获取当前用户信息
            User currentUser = TokenUtils.getCurrentUser();
            System.out.println(currentUser.getNickname());

            return Result.success(userService.page(new Page<>(pageNum, pageSize),queryWrapper));
        }

          @GetMapping("/export")
        public void export(HttpServletResponse response) throws Exception{
            List<User> list =userService.list();
            //通过工具类创建writer 写道磁盘路径
             /* ExcelWriter writer = ExcelUtil.getWriter("d:/writeBeanTest.xlsx");*/
              //在内存操作，写入浏览器
              ExcelWriter writer =ExcelUtil.getWriter(true);
              //自定义标题名
              writer.addHeaderAlias("username","用户名");
              writer.addHeaderAlias("password","密码");
              writer.addHeaderAlias("nickname","昵称");
              writer.addHeaderAlias("email","邮箱");
              writer.addHeaderAlias("phone","电话");
              writer.addHeaderAlias("address","地址");
              writer.addHeaderAlias("createTime","创建时间");
              writer.addHeaderAlias("avatarUrl","头像");

              writer.write(list,true);

              //response为HttpServletResponse对象
              response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
              String fileName= URLEncoder.encode("用户信息","UTF-8");
              //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
              response.setHeader("Content-Disposition","attachment;filename=test.xls");

              ServletOutputStream out=response.getOutputStream();

              writer.flush(out, true);
                // 关闭writer，释放内存
              out.close();
              writer.close();
          }

          @PostMapping("/import")
            public Result imp(MultipartFile file) throws Exception{
              InputStream inputStream = file.getInputStream();
              ExcelReader reader =ExcelUtil.getReader(inputStream);
              //方式一：通过javabean方式读取Excel对象，但表头要求英文要与javabean属性对于
              //List<User> list =reader.readAll(User.class);
              //方式二：忽略表头中文，直接读取表内容
              List<List<Object>> list=reader.read(1);
              List<User> users= CollUtil.newArrayList();
              for (List<Object> row :list){
                  User user=new User();
                  user.setUsername(row.get(0).toString());
                  user.setPassword(row.get(1).toString());
                  user.setNickname(row.get(2).toString());
                  user.setEmail(row.get(3).toString());
                  user.setPhone(row.get(4).toString());
                  user.setAddress(row.get(5).toString());
                  user.setAvatarUrl(row.get(6).toString());
                  users.add(user);
              }
              userService.saveBatch(users);
              return Result.success(true);
          }
}

