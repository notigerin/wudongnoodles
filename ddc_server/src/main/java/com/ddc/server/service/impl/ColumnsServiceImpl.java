package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.Columns;
import com.ddc.server.mapper.ColumnsMapper;
import com.ddc.server.service.ColumnsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ColumnsServiceImpl extends ServiceImpl<ColumnsMapper,Columns> implements ColumnsService {
    @Resource
    private ColumnsMapper columnsMapper;

    @Override
    public List<Columns> listAllColumns() {
        return columnsMapper.listAllColumns();
    }

    @Override
    public Columns selectColums(int id) {
        return columnsMapper.selectById(id);
    }

    @Override
    public int insertColumn(Columns columns) {
        int num = columnsMapper.insertColumn(columns);
        return num;
    }

    @Override
    public int deleteColumn(Columns columns) {
        int num = columnsMapper.deleteColumn(columns);
        return num;
    }

    @Override
    public List<Columns> searchColumn(String keyword) {
        List<Columns> columnsList = columnsMapper.searchColumn(keyword);
        return columnsList;
    }

    @Override
    public int updateColumn(Columns columns) {
        int num = columnsMapper.editColumn(columns);
        return num;
    }

}
