package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import javax.management.relation.Role;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@TableName("ddc_role")
public class DDCRole extends Model<DDCRole> {

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
     * 备注
     */
    private String remark;
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

    @TableField("role_level")
    private Integer roleLevel;

    @TableField(exist = false)
    private String adminName;

    @TableField(exist = false)
    private String authId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public DDCRole(String name, String remark){
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = 0L;
        this.name = name;
        this.remark = remark;
        this.createBy = 0L;
        this.updateBy = 0L;
        this.createTime = data.format(new Date(System.currentTimeMillis()));
        this.updateTime = data.format(new Date(System.currentTimeMillis()));
    }

    public DDCRole(Long id, String name, String remark){
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.updateTime = data.format(new Date(System.currentTimeMillis()));
    }
}
