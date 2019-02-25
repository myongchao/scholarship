package com.myc.scholarship.entity.dto;

import com.myc.scholarship.entity.Record;
import lombok.Data;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/2/25$ 9:39$
 */
@Data
public class ImportRecordDto extends Record {
    private String tips;
    private Boolean success;
}
