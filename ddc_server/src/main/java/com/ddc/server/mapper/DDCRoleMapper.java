package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCRole;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author MuQ
 * @since 2019-06-19
 */

public interface DDCRoleMapper extends BaseMapper<DDCRole> {
    List<DDCRole> getRoleList();

    List<DDCRole> getRoleName();

    DDCRole selectRoleId(@Param(value = "name") String name);

    void delById(@Param(value = "id") long id);

    List<DDCRole> selectRoleList(@Param(value = "roleLevel") Integer roleLevel);

}
