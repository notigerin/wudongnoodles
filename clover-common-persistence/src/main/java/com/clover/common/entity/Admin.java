package com.clover.common.entity;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 *  管理员表
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
@TableName("admin")
public class Admin extends Model<Admin> {

    private static final long serialVersionUID = 1L;
    /**
     * 用户主键
     */
    @TableId("id")
    private Integer id;
    /**
     * 姓名
     */
    @TableField("username")
    private String username;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 性别
     */
    @TableField("gender")
    private String gender;
    /**
     * 是电话号码，也是账号（登录用）
     */
    @TableField("phone")
    private String phone;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 状态值（1：启用，2：禁用）
     */
    @TableField("status")
    private Integer status;
    /**
     * 备注
     */
    @TableField("note")
    private String note;

    private Integer createBy;

    private long createTime;

    private Integer updateBy;

    private String updateTime;

    private Integer delFlag;

    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private String token;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
