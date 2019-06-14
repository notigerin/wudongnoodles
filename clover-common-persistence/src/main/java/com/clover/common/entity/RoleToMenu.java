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
 * 角色菜单表
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
@TableName("role_to_menu")
public class RoleToMenu extends Model<RoleToMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "role_to_menu_id", type = IdType.AUTO)
    private Integer roleToMenuId;
    /**
     * 角色代号
     */
    @TableField("role_id")
    private String roleID;
    /**
     * 菜单代号,规范权限标识
     */
    @TableField("menu_id")
    private String menuID;

    @Override
    protected Serializable pkVal() {
        return this.roleToMenuId;
    }

}
