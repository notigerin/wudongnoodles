package com.ddc.server.mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ddc.server.entity.Columns;

import java.util.List;

/**
 *
 * @author Lenove
 */
public interface ColumnsMapper extends BaseMapper<Columns> {
    List<Columns> listAllColumns();
    int insertColumn(Columns columns);
    int deleteColumn(Columns columns);
    List<Columns> searchColumn(String keyword);
    int editColumn(Columns columns);
}
