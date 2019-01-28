package com.myc.scholarship.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myc.scholarship.mian.entity.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Juci123
 * @since 2019-01-28
 */
@Data
@Accessors(chain = true)
@TableName("teacher")
public class Teacher extends FullAuditedEntity<Subject,Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 教师账号 */
    private String num;
    private String pwd;
    /**
     * 教师姓名
     */
    private String name;
    private String sex;
    private String contact;
    /**
     * 班级id
     */
    @TableField("classId")
    private Long classId;
    /**
     * 院系id
     */
    @TableField("depId")
    private Long depId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("insertTime")
    private Date insertTime;

    @TableField(exist = false)
    private Classroom classroom;

    @TableField(exist = false)
    private Department dep;

}
