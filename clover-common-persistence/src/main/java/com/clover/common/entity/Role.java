package com.clover.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author MuQ
 * @since 2019-06-14
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID主键
     */
    @TableId("id")
    private String roleId;

    /**
     * 角色名称
     */
    @TableField("name")
    private String roleName;

    /**
     * 角色描述
     */
    @TableField("describe")
    private  String describe;

    private Integer createBy;

    private String createTime;

    private Integer updateBy;

    private String updateTime;

    private Integer delFlag;

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
