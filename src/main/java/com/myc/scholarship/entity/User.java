package com.myc.scholarship.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;
import com.myc.scholarship.mian.entity.FullAuditedEntity;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("user")
public class User extends FullAuditedEntity<User,Long> implements Serializable {

    /**
     * 账号
     */
    private String num;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 角色
     */
    private String role;
    /**
     * 状态
     */
    private String status;


}
