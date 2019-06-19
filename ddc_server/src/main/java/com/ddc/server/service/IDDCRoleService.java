package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MuQ
 * @since 2019-06-17
 */
public interface IDDCRoleService extends IService<DDCRole> {
    DDCRole selectByRoleId(long id);

    List<DDCRole> selectAllRole();

    List<DDCRole> getRoleName();

    void insertRole(DDCRole role);

    void delRole(long id);

    void updateRole(DDCRole role);




}
