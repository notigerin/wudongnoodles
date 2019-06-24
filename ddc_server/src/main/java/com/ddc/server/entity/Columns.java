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

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("ddc_columns")


/**
 *  ddc_columns
 * @author 木瓜 2019-06-20
 */


public class Columns extends Model<Columns> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer columnId;

    @TableField("name")
    private String columnName;

    @TableField("level")
    private Integer columnLevel;

    @TableField("from")
    private Integer columnFrom;

    @TableField("create_by")
    private Long columnCreator;

    @TableField("create_time")
    private String columnCreateTime;

    @TableField("update_by")
    private Long columnUpdater;

    @TableField("update_time")
    private String columnUpdateTime;

    @TableField("del_flag")
    private Integer columnDeleteFlag;
    @TableField("type")
    private Integer columnType;

    @Override
    protected Serializable pkVal() {
        return this.columnId;
    }



    public Columns(String name, Integer type){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.columnName = name;
        this.columnType = type;
        this.columnCreateTime = df.format(new Date(System.currentTimeMillis()));
        this.columnCreator = 0L;
        this.columnUpdater = 0L;
    }

    public Columns(String name,int level,int from){
        this.columnName = name;
        this.columnLevel = level;
        this.columnFrom = from;
    }
}
