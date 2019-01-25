package com.myc.scholarship.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.myc.scholarship.mian.entity.FullAuditedEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Juci123
 * @since 2019-01-08
 */
@Data
@Accessors(chain = true)
@TableName("record")
public class Record extends FullAuditedEntity<Record,Long> implements Serializable {
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学生学号
     */
    private String num;
    /**
     * 奖项id
     */
    @TableField("award_id")
    private Long awardId;

    @TableField("class_id")
    private Long classId;

    @TableField("depId")
    private Long depId;
    /**
     * 申请时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("insertTime")
    private Date insertTime;
    /**
     * 辅导员审核状态
     */
    private String check1;
    /**
     * 教务处审核状态
     */
    private String check2;

    @TableField(exist = false)
    private Award award;

    @TableField(exist = false)
    private Score score;

    @TableField(exist = false)
    private Classroom classroom;

    @TableField(exist = false)
    private Department department;

}
