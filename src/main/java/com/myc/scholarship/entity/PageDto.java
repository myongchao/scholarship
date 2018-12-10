package com.myc.scholarship.entity;

import lombok.Data;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2018/12/1$ 14:40$
 */
@Data
public class PageDto {
    private  Integer current = 1;
    private  Integer size = 10;
    private String order = "creationTime";
}
