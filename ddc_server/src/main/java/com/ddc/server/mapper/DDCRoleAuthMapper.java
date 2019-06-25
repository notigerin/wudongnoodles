package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCRoleAuth;
import org.springframework.data.repository.query.Param;

/**
 * Mapper 接口
 *
 * @author dingpengfei
 * @since 2019-05-09
 */

public interface DDCRoleAuthMapper extends BaseMapper<DDCRoleAuth> {

    void delRoleAuthByRoleId(@Param(value = "roleId") Long roleId);
}
