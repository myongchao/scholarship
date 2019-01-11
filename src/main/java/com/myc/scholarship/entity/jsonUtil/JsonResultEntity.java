package com.myc.scholarship.entity.jsonUtil;

import lombok.Data;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/11$ 8:57$
 */
@Data
public class JsonResultEntity {
    private Object data;
    private Boolean success;
    private Integer code;
}
