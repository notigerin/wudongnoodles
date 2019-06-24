package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCZxCount;
import com.ddc.server.mapper.DDCZxCountMapper;
import com.ddc.server.service.IDDCZxCountService;
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
public class DDCZxCountServiceImpl extends ServiceImpl<DDCZxCountMapper, DDCZxCount> implements IDDCZxCountService {
    @Resource
    private DDCZxCountMapper zxcountMapper;
    @Override
    public ArrayList<DDCZxCount> selectAll(){
        return zxcountMapper.selectAll();
    }
}
