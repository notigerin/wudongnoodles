package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 权限表
 *
 * @author MuQ
 * @since 2019-06-17
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("ddc_authority")
public class DDCAuth extends Model<DDCAuth> {

    private static final long serialVersionUID = 1L;
    /**
     * 用户主键
     */
    @TableId("id")
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 权限标识
     */
    private String flag;
    /**
     * 上级权限
     */
    @TableField("p_id")
    private Long pId;
    /**
     * 权限
     */
    private Integer level;
    /**
     * 权限级别 1超级管理员 2管理员 3普通
     */
    @TableField("auth_level")
    private Integer authLevel;
    /**
     * 菜单URL
     */
    @TableField("menu_url")
    private String menuUrl;
    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;
    /**
     * 更新人
     */
    @TableField("update_by")
    private Long updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;
    /**
     * 删除标志
     */
    @TableField("del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private String pName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public DDCAuth(String name, String flag, Long pId, Integer level) {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = 0L;
        this.name = name;
        this.flag = flag;
        this.pId = pId;
        this.level = level;
        this.createTime = data.format(new Date(System.currentTimeMillis()));
        this.updateTime = data.format(new Date(System.currentTimeMillis()));
        this.createBy=0L;
        this.updateBy=0L;
        this.delFlag=0;
    }

    public DDCAuth(Long id, String name, String flag, Long pId, Integer level) {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = id;
        this.name = name;
        this.flag = flag;
        this.pId = pId;
        this.level = level;
        this.updateTime = data.format(new Date(System.currentTimeMillis()));
        this.updateBy=0L;
        this.delFlag=0;
    }
}
