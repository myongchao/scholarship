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
 * @since 2019-01-10
 */
@Data
@Accessors(chain = true)
@TableName("score")
public class Score extends FullAuditedEntity<Score,Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成绩编号
     */
    //private Long id;
    /**
     * 科目
     */
    private String subject;
    /**
     * 成绩分数
     */
    private Double score;
    /**
     * 学分
     */
    @TableField("subjectScore")
    private Integer subjectScore;
    /**
     * 综合成绩
     */
    @TableField("totalScore")
    private Double totalScore;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学生学号
     */
    private String num;
    /**
     * 班级
     */
    @TableField("stuClass")
    private String stuClass;
    /**
     * 成绩状态
     */
    private String status;
    /**
     * 名次
     */
    private Integer rank;

}
