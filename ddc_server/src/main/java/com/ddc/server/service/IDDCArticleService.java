package com.ddc.server.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCArticle;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author des
 * @since 2019-05-09
 */

public interface IDDCArticleService extends IService<DDCArticle>{

    List<DDCArticle> getAllArticle();

    DDCArticle getArticleByID(long aid);

    void delArticleByID(String aid);

    void addArticle(DDCArticle article);

    void updateArticle(DDCArticle article);
}
