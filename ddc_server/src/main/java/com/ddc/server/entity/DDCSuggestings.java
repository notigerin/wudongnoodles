package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;

/**
 *  ddc_suggestings
 * @author dingpengfei 2019-06-18
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("ddc_suggestings")
public class DDCSuggestings extends Model<DDCSuggestings> {
    private static final long serialVersionUID = 1L;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别 0 男 1 女 2 保密 默认保密
     */
    private Integer sex=2;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 附件地址
     */
    private String path;

    /**
     * 城市id
     */
    private Integer city;

    /**
     * 留言内容
     */
    private String remark;

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


    public DDCSuggestings(String username, Integer sex, String mobile, String email, String path, Integer city, String remark, Long createBy, Long updateBy) {
        this.username = username;
        this.sex = sex;
        this.mobile = mobile;
        this.email = email;
        this.path = path;
        this.city = city;
        this.remark = remark;
        this.createBy = createBy;
        this.createTime = System.currentTimeMillis();
        this.updateBy = updateBy;
        this.updateTime = System.currentTimeMillis();
        this.delFlag=0;
    }
}
