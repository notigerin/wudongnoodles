package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCRole;

import java.util.List;

/**
 *  服务类
 *
 * @author MuQ
 * @since 2019-06-19
 */
public interface IDDCRoleService extends IService<DDCRole> {
    DDCRole selectByRoleId(long id);

    List<DDCRole> selectAllRole();

    List<DDCRole> getRoleName();

    void insertRole(DDCRole role);

    DDCRole selectRoleId(String name);

    void delRole(long id);

    void updateRole(DDCRole role);

    List<DDCRole> selectRoleList(Integer roleLevel);




}
