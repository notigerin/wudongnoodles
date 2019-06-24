package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCZxCount;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Mapper 接口
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
public interface DDCZxCountMapper extends BaseMapper<DDCZxCount> {
    @Select("select * from zxcount")
    ArrayList<DDCZxCount>selectAll();

}
