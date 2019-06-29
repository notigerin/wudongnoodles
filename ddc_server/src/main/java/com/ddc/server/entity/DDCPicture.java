package com.ddc.server.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

/**
 * 图片表
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
@TableName("ddc_picture")
public class DDCPicture {
    /**
     * 用户主键
     */
    @TableId("id")
    private Long id;
    /**
     * 图片名
     */
    @TableField("name")
    private String name;
    /**
     * 封面
     */
    @TableField("image")
    private String image;
    /**
     * 图片
     */
    @TableField("more_image")
    private String moreImage;
    /**
     * 分类
     */
    @TableField("sort")
    private String sort;
    /**
     * 标签
     */
    @TableField("tags")
    private String tags;
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
     * 状态值（1：启用，2：禁用）
     */
    private Integer status;
    /**
     * 删除标志
     */
    @TableField("del_flag")
    private Integer delFlag;

}
