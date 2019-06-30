package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 前台用户表
 *
 * @author Dimo
 * @since 2019-06-14
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("user")
public class DDCMember extends Model<DDCMember> {

    private static final long serialVersionUID = 1L;
    /**
     * 用户表的主键为id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 是用户名，也是账号名称（登录用）
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册时间
     */
    @TableField("reg_time")
    private String registerTime;

    /**
     * 是否为会员（1为是，0为否）
     */
    @TableField("is_member")
    private int isMember;

    /**
     * 状态(1为已启用，0为未启用，2为已冻结）
     */
    private int status;

    /**
     * 积分（用户发布或者访问都会增加积分，积分>200作为会员）
     */
    private int integral;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 性别（默认为0，0表示男，1表示女）
     */
    private int gender;

    /**
     * 城市
     */
    private String city;

    /**
     * 身份证号
     */
    @TableField("identity_card")
    private String identityCard;

    /**
     * 联系手机
     */
    private String telephone;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 账号简介
     */
    private String resume;

    /**
     * 账号图标
     */
    private String icon;

    /**
     * 是否删除（默认为0，0为未删除，1位已删除）
     */
    @TableField("is_delete")
    private int isDelete;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * 更新人
     */
    @TableField("update_person")
    private Long updatePerson;

    /**
     * 创建人
     */
    private Long creater;

    /**
     * 更新时间
     */
    @TableField("time")
    private String time;

    /**
     * 登录成功之后生成字符串
     */
    @TableField(exist = false)
    private String token;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public DDCMember(String memberName){
        this.username=memberName;
    }

    public DDCMember(String memberName, int gender, String telephone, String email, String city, String beuzhu){
        this.username=memberName;
        this.gender=gender;
        this.telephone=telephone;
        this.email = email;
        this.city=city;
        this.address=beuzhu;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time=sdf.format(new Date());
        this.registerTime=this.time;
    }

}
