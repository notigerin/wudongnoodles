package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCRole;
import com.ddc.server.mapper.DDCRoleMapper;
import com.ddc.server.service.IDDCRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MuQ
 * @since 2019-06-17
 */
@Service
public class DDCRoleServiceImpl extends ServiceImpl<DDCRoleMapper, DDCRole> implements IDDCRoleService {
    @Resource
    private DDCRoleMapper roleMapper;

    @Override
    public DDCRole selectByRoleId(long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<DDCRole> selectAllRole() {
        return roleMapper.getRoleList();

    }

    @Override
    public List<DDCRole> getRoleName() {
        return roleMapper.getRoleName();
    }

    @Override
    public void insertRole(DDCRole role) {
        roleMapper.insert(role);
    }

    @Override
    public DDCRole selectRoleId(String name) {
        return roleMapper.selectRoleId(name);
    }

    @Override
    public void delRole(long id) {
        roleMapper.delById(id);
    }

    @Override
    public void updateRole(DDCRole role) {
        roleMapper.updateById(role);
    }
}
