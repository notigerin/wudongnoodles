package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCLog;
import com.ddc.server.mapper.DDCAdminMapper;
import com.ddc.server.mapper.DDCLogMapper;
import com.ddc.server.service.IDDCLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DDCLogServiceImpl extends ServiceImpl<DDCLogMapper, DDCLog> implements IDDCLogService {

    @Resource
    private DDCLogMapper logMapper;
    @Override
    public List<DDCLog> selectList(){
        return logMapper.selectList(new EntityWrapper<DDCLog>());
    }
}
//JDK1.8