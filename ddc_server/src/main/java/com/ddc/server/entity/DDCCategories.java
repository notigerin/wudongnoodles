package com.ddc.server.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("ddc_categories")
public class DDCCategories {
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
     * 上级权限
     */
    @TableField("p_id")
    private Long pId;
    /**
     * 权限级别
     */
    private Integer level;
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

    public DDCCategories(String name, String remark){
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = 0L;
        this.name = name;
        this.pId = 0L;
        this.level = 1;
        this.remark = remark;
        this.createBy = 0L;
        this.updateBy = 0L;
        this.createTime = data.format(new Date(System.currentTimeMillis()));
        this.updateTime = data.format(new Date(System.currentTimeMillis()));
    }
}
