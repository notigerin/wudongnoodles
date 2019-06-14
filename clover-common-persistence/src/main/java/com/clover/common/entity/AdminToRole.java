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
 * 管理员角色关系表
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
@TableName("admin_to_role")
public class AdminToRole extends Model<AdminToRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "user_to_role_id", type = IdType.AUTO)
    private Integer userToRoleId;
    /**
     * 管理员ID
     */
    @TableField("admin_id")
    private String userNo;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleCode;

    @Override
    protected Serializable pkVal() {
        return this.userToRoleId;
    }
}
