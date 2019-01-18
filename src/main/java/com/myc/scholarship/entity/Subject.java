package com.myc.scholarship.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

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
 * @since 2019-01-17
 */
@Data
@Accessors(chain = true)
@TableName("subject")
public class Subject extends FullAuditedEntity<Subject,Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 课程编号
     */
    private String code;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 学分
     */
    @TableField("subjectScore")
    private Integer subjectScore;


}
