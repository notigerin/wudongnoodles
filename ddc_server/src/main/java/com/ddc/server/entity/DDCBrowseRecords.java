package com.ddc.server.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

/**
 *  浏览记录表
 *
 * @author MuQ
 * @since 2019-06-30
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("browse_records")
public class DDCBrowseRecords {
    /**
     * 主键
     */
    @TableId("id")
    private Long id;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 资讯ID
     */
    @TableField("article_id")
    private Long articleId;
    /**
     * IP
     */
    @TableField("ip")
    private String ip;
    /**
     * 浏览时间
     */
    @TableField("visit_time")
    private String visitTime;


}
