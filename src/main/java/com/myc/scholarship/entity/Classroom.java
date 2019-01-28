package com.myc.scholarship.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;

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
 * @since 2019-01-12
 */
@Data
@Accessors(chain = true)
@TableName("classroom")
public class Classroom extends FullAuditedEntity<Classroom,Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级名次
     */
    private String name;

    @TableField("teacher_id")
    private Long teacherId;

    @TableField("dep_id")
    private Long depId;
    private Integer places;

    @TableField(exist = false)
    private Teacher teacher;

    @TableField(exist = false)
    private Department department;

}
