package com.myc.scholarship.entity.dto;

import com.myc.scholarship.entity.Student;
import lombok.Data;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/24$ 10:26$
 */
@Data
public class ImportStudentDto extends Student {
    private String tips;
    private Boolean success;
}
