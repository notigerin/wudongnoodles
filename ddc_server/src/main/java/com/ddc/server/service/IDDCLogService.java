package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCLog;

import java.util.List;

public interface IDDCLogService extends IService<DDCLog> {
    List<DDCLog> selectList();
}
