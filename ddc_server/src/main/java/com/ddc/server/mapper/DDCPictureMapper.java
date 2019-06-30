package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCPicture;
import org.springframework.data.repository.query.Param;

public interface DDCPictureMapper extends BaseMapper<DDCPicture> {


    void updateStatus(@Param(value = "id") Long id, @Param(value = "status")Integer status);
}
