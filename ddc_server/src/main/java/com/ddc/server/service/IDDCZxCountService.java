package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCZxCount;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
public interface IDDCZxCountService extends IService<DDCZxCount> {
    ArrayList<DDCZxCount>selectAll();
}
