package com.ybh.codeGenerator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ybh.blog.BlogApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest(classes = BlogApplication.class)
public class codeGenerator {
    @Test
    public void codeGeneral() {
        FastAutoGenerator.create("jdbc:mysql://124.222.129.50:3306/blog?serverTimezone=Asia/Shanghai", "root", "root")
                //1.全局配置
                .globalConfig(builder -> {
                    builder.author("Altria") //作者
                            .enableSwagger() //开启swagger模式
                            .commentDate("yyyy-MM-dd")
                            .fileOverride() //覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java"); //指定输出目录

                })
                //2.包配置
                .packageConfig(builder -> {
                    builder.parent("com.ybh") //设置父包名
                            .moduleName("blog") //设父包模块名
                            .entity("entity") // 设置实体类包名
                            .service("service") // 设置service层包名
                            .serviceImpl("impl") // 设置serviceImpl层包名
                            .controller("controller") // 设置controller层包名
                            .mapper("mapper") // 设置mapper层包名
                            .xml("mapper") // 设置mapper.xml文件包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper")); // 更改mapper.xml在resources目录下
                })
                //3.策略配置
                .strategyConfig(builder -> {
                    builder.addInclude() //设置需要生成的表名，不填入表名默认生成数据库中全表
                            .addTablePrefix("blog_") //过滤表前缀
                            //设置service层配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            //设置entity层配置
                            .entityBuilder()
                            .enableLombok() //开启lombok插件
                            .logicDeleteColumnName("is_deleted") //配置表中逻辑删除字段
                            //设置controller层配置
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle() //使用RestController
                            //设置mapper层配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation() //使用@Mapper注解
                            .formatXmlFileName("%sMapper");
                })
                //4.模板引擎配置
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的为Velicity
                //5.执行
                .execute();
    }
}

