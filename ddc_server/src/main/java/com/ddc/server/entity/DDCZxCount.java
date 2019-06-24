package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 管理员
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName("zxcount")
public class DDCZxCount extends Model<DDCZxCount> {

    private static final long serialVersionUID = 1L;

    private String month2;
    /**
     * 创建时间
     */

    private int count2;

    @Override
    protected Serializable pkVal() {
        return this.count2;
    }



    public DDCZxCount(String month2,int count2) {
        this.month2=month2;
        this.count2 =count2;

    }
}
