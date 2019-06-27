package com.ddc.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.mapper.DDCAdminMapper;
import com.ddc.server.service.IDDCAdminService;
import com.ddc.server.shiro.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MuQ
 * @since 2019-06-17
 */
@Service
public class DDCAdminServiceImpl extends ServiceImpl<DDCAdminMapper, DDCAdmin> implements IDDCAdminService {
    @Resource
    private DDCAdminMapper adminMapper;
    @Override
    public DDCAdmin selectByName(String userNo) {
        return adminMapper.selectOne(new DDCAdmin(userNo));
    }

    @Override
    public Map<String, Object> checkNameAndPasswd(JSONObject requestJson) {
        //todo 检查用户名密码是否匹配 如果是反正Admin对象信息
        return null;
    }

    @Override
    public void updateStatus(long id, Integer status) {
        adminMapper.updateStatus(id, status);
    }

    @Override
    public List<DDCAdmin> selectByRoleId(Long roleId) {
        return adminMapper.selectByRoleId(roleId);
    }


    @Override
    public List<DDCAdmin> selectAllAdmin(){
        return adminMapper.getAdminList();
    }

    @Override
    public void insertAdmin(DDCAdmin admin){
        PasswordUtils.entryptPassword(admin);
        adminMapper.insert(admin);
    }

    @Override
    public void delAdmin(long id){
        adminMapper.delAdmin(id);
    }

    @Override
    public void updateAdmin(DDCAdmin admin){
        if(!StringUtils.isEmpty(admin.getPassword())) {
            PasswordUtils.entryptPassword(admin);
        }
        adminMapper.updateById(admin);
    }
}
