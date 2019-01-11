package com.myc.scholarship.entity.token;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 登录验证后返回的信息
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/9$ 9:26$
 */
@Data
public class UserInfoToken {
    Integer code;
    Map<String,String> data;
}
