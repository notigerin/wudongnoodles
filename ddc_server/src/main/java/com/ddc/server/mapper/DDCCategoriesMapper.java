package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCCategories;

import java.util.List;

public interface DDCCategoriesMapper extends BaseMapper<DDCCategories> {
    List<DDCCategories> getCategoriesList();
}

