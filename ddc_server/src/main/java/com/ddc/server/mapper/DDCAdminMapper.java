package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCAdmin;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
public interface DDCAdminMapper extends BaseMapper<DDCAdmin> {

    List<DDCAdmin> getAdminList();

    DDCAdmin selectAdmin(@Param(value = "name") String name);

    void updateStatus(@Param(value = "id") long id, @Param(value = "status") Integer status);

    void delAdmin(@Param(value = "id") long id);

    List<DDCAdmin> selectByRoleId(@Param(value = "roleId") Long roleId);
}
