package com.myc.scholarship.config;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/14$ 9:36$
 */

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig{
        /** 
      *   mybatis-plus分页插件 
      */
        @Bean
        public PaginationInterceptor paginationInterceptor(){
           PaginationInterceptor page = new PaginationInterceptor();
           page.setDialectType("mysql");
           return page;
         }
        }
