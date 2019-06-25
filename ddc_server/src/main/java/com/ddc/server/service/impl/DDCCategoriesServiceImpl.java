package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCCategories;
import com.ddc.server.mapper.DDCCategoriesMapper;
import com.ddc.server.service.IDDCCategoriesService;

import javax.annotation.Resource;
import java.util.List;

public class DDCCategoriesServiceImpl extends ServiceImpl<DDCCategoriesMapper, DDCCategories> implements IDDCCategoriesService {
    @Resource
    private DDCCategoriesMapper categoriesMapper;

    @Override
    public List<DDCCategories> findAllCategories() {
        return categoriesMapper.getCategoriesList();
    }

    @Override
    public void addCategories(DDCCategories categories) {
        categoriesMapper.insert(categories);
    }
}
