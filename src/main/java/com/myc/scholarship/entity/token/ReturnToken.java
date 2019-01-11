package com.myc.scholarship.entity.token;

import lombok.Data;

import java.util.Map;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 获取 token
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/9$ 10:00$
 */
@Data
public class ReturnToken {
    Integer code;
    TokenData data;
}
