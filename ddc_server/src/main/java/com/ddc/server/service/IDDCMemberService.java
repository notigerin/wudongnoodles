package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCMember;

import java.util.List;
/**
 *  服务类
 *
 * @author MuQ
 * @since 2019-06-30
 */
public interface IDDCMemberService extends IService<DDCMember> {
    List<DDCMember> selectMemberList();

    void updateStatus(long id, Integer status);

    void editPasswordById(Long id, String password);
}
