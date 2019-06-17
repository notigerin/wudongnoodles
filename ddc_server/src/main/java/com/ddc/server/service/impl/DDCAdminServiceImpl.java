package com.ddc.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.mapper.DDCAdminMapper;
import com.ddc.server.service.IDDCAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@Service
public class DDCAdminServiceImpl extends ServiceImpl<DDCAdminMapper, DDCAdmin> implements IDDCAdminService {
@Resource private DDCAdminMapper adminMapper;
    @Override
    public DDCAdmin selectByName(String userNo) {
        return adminMapper.selectOne(new DDCAdmin(userNo));
    }

    @Override
    public Map<String, Object> checkNameAndPasswd(JSONObject requestJson) {
        //todo 检查用户名密码是否匹配 如果是反正Admin对象信息
        return null;
    }
}
