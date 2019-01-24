package com.myc.scholarship.entity.dto;

import com.myc.scholarship.entity.Student;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/24$ 10:39$
 */
@Data
public class ListStudentDto {
    @Valid
    private List<Student> data;
}
