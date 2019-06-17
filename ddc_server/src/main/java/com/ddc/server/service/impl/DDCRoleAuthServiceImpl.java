package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCRoleAuth;
import com.ddc.server.mapper.DDCRoleAuthMapper;
import com.ddc.server.service.IDDCRoleAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@Service
public class DDCRoleAuthServiceImpl extends ServiceImpl<DDCRoleAuthMapper, DDCRoleAuth> implements IDDCRoleAuthService {
    @Resource
    private DDCRoleAuthMapper roleAuthMapper;

    @Override
    public void insertRoleAuth(DDCRoleAuth roleAuth) {
        roleAuthMapper.insert(roleAuth);
    }

    @Override
    public void delRoleAuth(long id) {
        roleAuthMapper.deleteById(id);
    }
}
