package com.myc.scholarship.entity;

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
@TableName("department")
public class Department extends FullAuditedEntity<Department,Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 院系名称
     */
    private String name;


}
