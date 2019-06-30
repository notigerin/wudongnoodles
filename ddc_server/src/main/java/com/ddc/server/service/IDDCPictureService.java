package com.ddc.server.service;


import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCPicture;
/**
 *  服务类
 *
 * @author MuQ
 * @since 2019-06-30
 */
public interface IDDCPictureService extends IService<DDCPicture> {
    void updateStatus(Long id, Integer status);
}
