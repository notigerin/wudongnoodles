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
     * 权限ID
     */
    @TableId("permissions_id")
    private Integer permissionsId;
    /**
     * 父权限ID
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 权限名称
     */
    @TableField("name")
    private String name;
    /**
     * 权限级别
     */
    @TableField("level")
    private String level;
    /**
     * 权限标识
     */
    @TableField("flag")
    private String flag;

    private Integer createBy;

    private String createTime;

    private Integer updateBy;

    private String updateTime;

    private Integer delFlag;

    @Override
    protected Serializable pkVal() {
        return this.permissionsId;
    }

}
