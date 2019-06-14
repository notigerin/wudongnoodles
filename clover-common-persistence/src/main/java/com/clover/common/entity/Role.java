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
 * @author dingpengfei
 * @since 2019-05-09
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
    private String roleID;

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

    @Override
    protected Serializable pkVal() {
        return this.roleID;
    }

}
