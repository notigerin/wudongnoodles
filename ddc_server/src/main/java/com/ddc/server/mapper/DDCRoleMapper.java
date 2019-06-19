package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCRole;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author dingpengfei
 * @since 2019-05-09
 */

public interface DDCRoleMapper extends BaseMapper<DDCRole> {
    List<DDCRole> getRoleList();

    List<DDCRole> getRoleName();

}
