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
 * 菜单表
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
@TableName("menu")
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
    private String url;
    private String code;


    @TableField(exist = false)
    private List<Menu> childMenu;

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
