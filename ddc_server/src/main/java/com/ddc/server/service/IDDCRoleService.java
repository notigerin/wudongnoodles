package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
public interface IDDCRoleService extends IService<DDCRole> {
    DDCRole selectById(long id);

    List<DDCRole> selectAllRole();

    void insertRole(DDCRole role);

    void delRole(long id);

    void updateRole(DDCRole role);




}
