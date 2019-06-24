package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.*;

import java.io.DataInput;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("ddc_article")
/**
 *  ddc_article
 * @author 大狼狗 2019-06-18
 */
@Data
public class DDCArticle extends Model<DDCArticle> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 简略标题
     */
    private String shortTitle;

    /**
     * 分类栏目
     */
    private String classify;

    /**
     * 文章类型
     */
    private String type;

    /**
     * 排序值
     */
    private Integer sortValue;

    /**
     * 关键词
     */
    private String keyWord;

    /**
     * 文章摘要
     */
    private String abstracts;

    /**
     * 文章作者
     */
    private String writer;

    /**
     * 文章来源
     */
    private String resource;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 次数
     */
    private Integer frequency;

    /**
     * 状态标志位
     */
    @TableField("state_flag")
    private Integer stateFlag;

    /**
     * create_by
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * create_time
     */
    @TableField("create_time")
    private String createTime;

    /**
     * update_by
     */
    @TableField("update_by")
    private Long updateBy;

    /**
     * update_time
     */
    @TableField("update_time")
    private String updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public DDCArticle(long id,String title,String shortTitle, String classify, String type, Integer sortValue,
                      String keyWord, String abstracts, String writer,String resource, String content) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id=id;
        this.title = title;
        this.shortTitle=shortTitle;
        this.classify = classify;
        this.type=type;
        this.sortValue=sortValue;
        this.keyWord=keyWord;
        this.abstracts=abstracts;
        this.writer=writer;
        this.resource=resource;
        this.content=content;
        this.frequency = 0;
        this.updateTime=df.format(new Date(System.currentTimeMillis()));
        this.createBy=0L;
        this.updateBy=0L;
        this.stateFlag=0;
    }

    public DDCArticle(String title,String shortTitle, String classify, String type, Integer sortValue,
                      String keyWord, String abstracts, String writer,String resource, String content) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id=0;
        this.title = title;
        this.shortTitle=shortTitle;
        this.classify = classify;
        this.type=type;
        this.sortValue=sortValue;
        this.keyWord=keyWord;
        this.abstracts=abstracts;
        this.writer=writer;
        this.resource=resource;
        this.content=content;
        this.frequency = 0;
        this.createTime=df.format(new Date(System.currentTimeMillis()));
        this.updateTime=df.format(new Date(System.currentTimeMillis()));
        this.createBy=0L;
        this.updateBy=0L;
        this.stateFlag=0;
    }

}