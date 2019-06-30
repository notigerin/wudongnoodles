package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCPicture;
import com.ddc.server.mapper.DDCPictureMapper;
import com.ddc.server.service.IDDCPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  服务实现类
 *
 * @author MuQ
 * @since 2019-06-30
 */
@Service
public class DDCPictureServiceImpl extends ServiceImpl<DDCPictureMapper, DDCPicture> implements IDDCPictureService {
    @Resource
    DDCPictureMapper pictureMapper;


    @Override
    public void updateStatus(Long id, Integer status) {
        pictureMapper.updateStatus(id,status);
    }
}
