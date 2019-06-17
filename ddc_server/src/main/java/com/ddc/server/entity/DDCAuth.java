package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 权限表
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
     * 权限级别
     */
    private Integer level;


    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;
    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;
    /**
     * 更新人
     */
    @TableField("update_by")
    private Long updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Long updateTime;


    /**
     * 删除标志
     */
    @TableField("del_flag")
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public DDCAuth(String name, String flag, Long pId, Integer level) {
        this.name = name;
        this.flag = flag;
        this.pId = pId;
        this.level = level;
        this.createTime=System.currentTimeMillis();
        this.updateTime=System.currentTimeMillis();
        this.createBy=0L;
        this.updateBy=0L;
        this.delFlag=0;
    }
}
