package com.clover.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 角色权限表
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
@TableName("role_to_permissions")
public class RoleToMenu extends Model<RoleToMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "role_to_permissions_id", type = IdType.AUTO)
    private Integer roleToPermissionsId;
    /**
     * 角色代号
     */
    @TableField("role_id")
    private String roleId;
    /**
     * 菜单代号,规范权限标识
     */
    @TableField("permissions_id")
    private String permissionsId;

    @Override
    protected Serializable pkVal() {
        return this.roleToPermissionsId;
    }

}
