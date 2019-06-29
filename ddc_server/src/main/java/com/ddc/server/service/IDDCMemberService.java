package com.ddc.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCMember;

import java.util.List;

public interface IDDCMemberService extends IService<DDCMember> {
    List<DDCMember> selectMemberList();

    void updateStatus(long id, Integer status);
}
