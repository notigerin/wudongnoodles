package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCCategories;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MuQ
 * @since 2019-06-17
 */
public interface IDDCCategoriesService extends IService<DDCCategories> {
    List<DDCCategories> findAllCategories();

    void addCategories(DDCCategories categories);
}
