package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCAuth;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
public interface IDDCAuthService extends IService<DDCAuth> {

    List<DDCAuth> selectAllAuth();

    void insertAuth(DDCAuth auth);

    void delAuth(long id);

    void updateAuth(DDCAuth auth);

    List<DDCAuth> selectByRoleId(long roleId);
}
