package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCAuth;
import com.ddc.server.entity.DDCRoleAuth;
import com.ddc.server.mapper.DDCAuthMapper;
import com.ddc.server.mapper.DDCRoleAuthMapper;
import com.ddc.server.service.IDDCAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@Service
public class DDCAuthServiceImpl extends ServiceImpl<DDCAuthMapper, DDCAuth> implements IDDCAuthService {
    @Resource
    private DDCAuthMapper authMapper;
    @Resource
    private DDCRoleAuthMapper roleAuthMapper;

    @Override
    public List<DDCAuth> selectByRoleId(Long roleId) {
        List<DDCRoleAuth> roleAuths = roleAuthMapper.selectList(new EntityWrapper<DDCRoleAuth>().eq("role_id", roleId));
        List<DDCAuth> auths = new ArrayList<>(10);
        for (DDCRoleAuth roleAuth : roleAuths) {
            auths.add(authMapper.selectById(roleAuth.getAuthId()));
        }
        return auths;
    }
}
