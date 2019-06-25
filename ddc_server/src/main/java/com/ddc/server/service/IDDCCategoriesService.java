package com.ddc.server.service;

import com.ddc.server.entity.DDCCategories;

import java.util.List;

public interface IDDCCategoriesService {
    List<DDCCategories> findAllCategories();

    void addCategories(DDCCategories categories);
}
