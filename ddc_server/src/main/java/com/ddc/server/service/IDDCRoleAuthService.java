package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCRoleAuth;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MuQ
 * @since 2019-06-17
 */
public interface IDDCRoleAuthService extends IService<DDCRoleAuth> {

    void insertRoleAuth(DDCRoleAuth roleAuth);

    void delRoleAuth(long id);

}
