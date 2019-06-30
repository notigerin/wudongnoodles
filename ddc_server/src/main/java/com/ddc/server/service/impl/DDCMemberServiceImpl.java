package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCMember;
import com.ddc.server.mapper.DDCMemberMapper;
import com.ddc.server.service.IDDCMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  服务实现类
 *
 * @author Dimo
 * @since 2019-06-18
 */
@Service
public class DDCMemberServiceImpl extends ServiceImpl<DDCMemberMapper, DDCMember> implements IDDCMemberService {
    @Resource
    private DDCMemberMapper memberMapper;
    @Override
    public List<DDCMember> selectMemberList(){
        return memberMapper.selectAll();
    }
    @Override
    public void updateStatus(long id, Integer status) {
        memberMapper.updateStatus(id, status);
    }

    @Override
    public void editPasswordById(Long id, String password) {
        memberMapper.updatePassword(id,password);
    }
}
