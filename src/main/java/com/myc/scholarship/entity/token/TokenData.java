package com.myc.scholarship.entity.token;

import lombok.Data;

import java.util.List;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/9$ 9:54$
 */
@Data
public class TokenData {
    List<String> roles;
    String name;
    String avatar;
}
