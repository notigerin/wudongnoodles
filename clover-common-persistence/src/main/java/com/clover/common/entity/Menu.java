package com.clover.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 权限表
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
@TableName("permissions")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Integer menuId;
    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;
    /**
     * 菜单代号,规范权限标识
     */
    @TableId("level")
    private String menuLevel;
    /**
     * 菜单地址
     */
    private String flag;

    private Integer create_by;

    private String create_time;

    private Integer update_by;

    private String update_time;

    private Integer del_flag;

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
