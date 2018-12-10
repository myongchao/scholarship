package com.myc.scholarship.mian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2018/11/30$ 13:23$
 */
public class FullAuditedEntity<T extends Model, P extends Serializable> extends AuditedEntity<T, P> {
    @TableField("isDeleted")
    @TableLogic
    private boolean isDeleted;
    @TableField("deleterUserId")
    private String deleterUserId;
    @TableField("deletionTime")
    private Date deletionTime;

    public FullAuditedEntity() {
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public String getDeleterUserId() {
        return this.deleterUserId;
    }

    public void setDeleterUserId(String deleterUserId) {
        this.deleterUserId = deleterUserId;
    }

    public Date getDeletionTime() {
        return this.deletionTime;
    }

    public void setDeletionTime(Date deletionTime) {
        this.deletionTime = deletionTime;
    }
}
