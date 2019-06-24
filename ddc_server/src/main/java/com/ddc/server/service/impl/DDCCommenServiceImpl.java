package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.Commen;
import com.ddc.server.mapper.DDCCommenMapper;
import com.ddc.server.service.IDDCCommenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DDCCommenServiceImpl extends ServiceImpl<DDCCommenMapper, Commen> implements IDDCCommenService {
    @Resource
    private DDCCommenMapper ddcCommenMapper;

}
