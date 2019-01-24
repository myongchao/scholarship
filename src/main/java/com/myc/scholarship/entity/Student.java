package com.myc.scholarship.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.myc.scholarship.mian.entity.FullAuditedEntity;
import lombok.Data;
import lombok.Lombok;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author Juci123
 * @since 2018-11-25
 */
@Data
@Accessors(chain = true)
@TableName("student")
public class Student extends FullAuditedEntity<Student,Long> implements Serializable {
    /**
     * 学号
     */
    private String num;
    /**
     * 姓名
     */
    private String name;
    /**
     * 院系
     */
    @TableField("depId")
    private Long depId;
    /**
     * 班级
     */
    @TableField("class_id")
    private Long classId;
    /**
     * 层次
     */
    private String level;
    /**
     * 状态
     */
    @TableField("stuState")
    private String stuState;
    /**
     * 家庭户口
     */
    @TableField("familyAccount")
    private String familyAccount;
    /**
     * 人数
     */
    @TableField("familyNum")
    private Integer familyNum;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮编
     */
    @TableField("zipCode")
    private String zipCode;
    /**
     * 收入
     */
    @TableField("inCome")
    private String inCome;
    /**
     * 收入来源
     */
    @TableField("inComeFrom")
    private String inComeFrom;

    @TableField(exist = false)
    private Classroom classroom;
    @TableField(exist = false)
    private Department dep;
    @TableField(exist = false)
    private String className;
    @TableField(exist = false)
    private String depName;
//    @TableField(exist = false)
//    private List<Score> listScore;

}
