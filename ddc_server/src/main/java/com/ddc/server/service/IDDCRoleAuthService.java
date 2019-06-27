package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCRoleAuth;

/**
 *  服务类
 *
 * @author MuQ
 * @since 2019-06-19
 */
public interface IDDCRoleAuthService extends IService<DDCRoleAuth> {

    void insertRoleAuth(Long roleId, String authIdItems);

    void delRoleAuth(long id);

    void delRoleAuthByRoleId(Long roleId);

}
