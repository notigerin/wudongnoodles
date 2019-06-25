package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCSuggestings;
import com.ddc.server.mapper.DDCSuggestingsMapper;
import com.ddc.server.service.IDDCSuggestingsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@Service
public class DDCSuggestingsServiceImpl extends ServiceImpl<DDCSuggestingsMapper, DDCSuggestings> implements IDDCSuggestingsService {
    @Resource
    private DDCSuggestingsMapper suggestingsMapper;

}
