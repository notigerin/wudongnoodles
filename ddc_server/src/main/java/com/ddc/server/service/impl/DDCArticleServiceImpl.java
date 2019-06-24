package com.ddc.server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCArticle;
import com.ddc.server.mapper.DDCArticleMapper;
import com.ddc.server.service.IDDCArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author des
 * @since 2019-05-09
 */
@Service
public class DDCArticleServiceImpl extends ServiceImpl<DDCArticleMapper, DDCArticle> implements IDDCArticleService {
@Resource private DDCArticleMapper articleMapper;

    @Override
    public List<DDCArticle> getAllArticle(){
        return articleMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public DDCArticle getArticleByID(long aid){
        return articleMapper.selectById(aid);
    }

    @Override
    public void delArticleByID(String aid){
        long id=Long.parseLong(aid);
        articleMapper.deleteById(id);
    }

    @Override
    public void addArticle(DDCArticle article){
        articleMapper.insert(article);
    }

    @Override
    public void updateArticle(DDCArticle article){
        articleMapper.updateById(article);
    }
}
