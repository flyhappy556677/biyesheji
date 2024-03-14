package com.example.dronemanagerhou.utils;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {

    public static  void  main(String[] args) {
        generate();
    }

        private static void generate(){
            FastAutoGenerator.create("jdbc:mysql://localhost:3306/drone?serverTimezone=GMT%2b8", "root", "123456")
                    .globalConfig(builder -> {
                        builder.author("drone") // 设置作者
                                .enableSwagger() // 开启 swagger 模式
                                .fileOverride() // 覆盖已生成文件
                                .outputDir("D:\\Code\\biyesheji\\drone-manager-hou\\src\\main\\java\\"); // 指定输出目录
                    })

                    .packageConfig(builder -> {
                        builder.parent("com.example.dronemanagerhou") // 设置父包名
                                .moduleName("") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\Code\\biyesheji\\drone-manager-hou\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                    })
                    .strategyConfig(builder -> {
                        builder.addInclude("sys_user") // 设置需要生成的表名
                                .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    })
                    .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                    .execute();

        }

}

