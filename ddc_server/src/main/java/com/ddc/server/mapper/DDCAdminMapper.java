package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCAdmin;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper 接口
 *
 * @author MuQ
 * @since 2019-06-19
 */
public interface DDCAdminMapper extends BaseMapper<DDCAdmin> {

    List<DDCAdmin> getAdminList();

    DDCAdmin selectAdmin(@Param(value = "name") String name);

    void updateStatus(@Param(value = "id") long id, @Param(value = "status") Integer status);

    void delAdmin(@Param(value = "id") long id);

    List<DDCAdmin> selectByRoleId(@Param(value = "roleId") Long roleId);

    List<DDCAdmin> selectAdminList(Map<String, Object> map);

    Integer selectAdminCount(Map<String, Object> map);
}
