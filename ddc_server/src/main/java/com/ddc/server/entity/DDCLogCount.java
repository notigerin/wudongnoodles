package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
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
@TableName("logincount")
public class DDCLogCount extends Model<DDCLogCount> {

    private static final long serialVersionUID = 1L;

    private String month1;
    /**
     * 创建时间
     */

    private int count;

    @Override
    protected Serializable pkVal() {
        return this.count;
    }



    public DDCLogCount(String month1, int count) {
        this.month1=month1;
        this.count =count;

    }
}
