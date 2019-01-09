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
 * @since 2018-12-01
 */
@Data
@Accessors(chain = true)
@TableName("award")
public class Award extends FullAuditedEntity<Award,Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 奖项编号
     */
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;
    /**
     * 奖项名称
     */
    private String title;
    /**
     * 奖项等级
     */
    @TableField("bGrade")
    private String bGrade;
    /**
     * 奖项金额
     */
    private Double amount;
    /**
     * 最低分数
     */
    @TableField("minScore")
    private Double minScore;


}
