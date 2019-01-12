package com.myc.scholarship.mian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2018/11/30$ 13:25$
 */
public class Entity<T extends Model, P extends Serializable> extends Model<T> {
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private P id;
    private String tenantId;

    public Entity() {
    }

    public P getId() {
        return this.id;
    }

    public void setId(P id) {
        this.id = id;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public String setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return null;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
