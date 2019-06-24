package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCLogCount;
import com.ddc.server.mapper.DDCLogCountMapper;
import com.ddc.server.service.IDDCLogCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@Service
public class DDCLogCountServiceImpl extends ServiceImpl<DDCLogCountMapper, DDCLogCount> implements IDDCLogCountService {
    @Resource
    private DDCLogCountMapper logincountMapper;
    @Override
    public ArrayList<DDCLogCount> selectAll(){
        return logincountMapper.selectAll();
    }
}
