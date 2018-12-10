package com.myc.scholarship.mian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2018/11/30$ 13:24$
 */
public class CreationAuditedEntity<T extends Model, P extends Serializable> extends Entity<T, P> {
    @TableField(
            fill = FieldFill.INSERT,
            value = "creationTime"
    )
    private Date creationTime;
    @TableField(
            value = "creatorUserId",
            fill = FieldFill.INSERT
    )
    private String creatorUserId;

    public CreationAuditedEntity() {
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreatorUserId() {
        return this.creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }
}
