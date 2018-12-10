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
public class AuditedEntity<T extends Model, P extends Serializable> extends CreationAuditedEntity<T, P> {
    @TableField(
            value = "lastModificationTime",
            fill = FieldFill.INSERT_UPDATE
    )
    private Date lastModificationTime;
    @TableField(
            value = "lastModefierUserId",
            fill = FieldFill.INSERT_UPDATE
    )
    private String lastModefierUserId;

    public AuditedEntity() {
    }

    public Date getLastModificationTime() {
        return this.lastModificationTime;
    }

    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public String getLastModefierUserId() {
        return this.lastModefierUserId;
    }

    public void setLastModefierUserId(String lastModefierUserId) {
        this.lastModefierUserId = lastModefierUserId;
    }
}
