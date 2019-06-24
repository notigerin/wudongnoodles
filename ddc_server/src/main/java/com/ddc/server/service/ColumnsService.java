package com.ddc.server.service;

import com.ddc.server.entity.Columns;

import java.io.IOException;
import java.util.List;

public interface ColumnsService {
    List<Columns> listAllColumns();
    Columns selectColums(int id);
    int insertColumn(Columns columns) throws IOException;
    int deleteColumn(Columns columns);
    List<Columns> searchColumn(String keyword);
    int updateColumn(Columns columns);
}
