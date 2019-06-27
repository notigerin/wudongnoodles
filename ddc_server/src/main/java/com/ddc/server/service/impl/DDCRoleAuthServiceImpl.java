package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCRoleAuth;
import com.ddc.server.mapper.DDCRoleAuthMapper;
import com.ddc.server.service.IDDCRoleAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  服务实现类
 *
 * @author MuQ
 * @since 2019-06-19
 */
@Service
public class DDCRoleAuthServiceImpl extends ServiceImpl<DDCRoleAuthMapper, DDCRoleAuth> implements IDDCRoleAuthService {
    @Resource
    private DDCRoleAuthMapper roleAuthMapper;

    @Override
    public void insertRoleAuth(Long roleId, String authIdItems) {
        DDCRoleAuth roleAuth;
        String[] strs = authIdItems.split(",");
        for (int i = 0; i < strs.length; i++) {
            long authId = Long.parseLong(strs[i]);
            roleAuth = new DDCRoleAuth(roleId,authId);
            roleAuthMapper.insert(roleAuth);
        }

    }

    @Override
    public void delRoleAuth(long id) {
        roleAuthMapper.deleteById(id);
    }

    @Override
    public void delRoleAuthByRoleId(Long roleId) {
        roleAuthMapper.delRoleAuthByRoleId(roleId);
    }
}
