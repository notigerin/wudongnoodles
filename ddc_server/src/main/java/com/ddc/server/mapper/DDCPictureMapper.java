package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCPicture;
import org.springframework.data.repository.query.Param;
/**
 *  Mapper 接口
 *
 * @author MuQ
 * @since 2019-06-30
 */
public interface DDCPictureMapper extends BaseMapper<DDCPicture> {


    void updateStatus(@Param(value = "id") Long id, @Param(value = "status")Integer status);
}
