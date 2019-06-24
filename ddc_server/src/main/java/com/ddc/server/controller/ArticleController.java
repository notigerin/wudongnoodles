package com.ddc.server.controller;

import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCArticle;
import com.ddc.server.service.IDDCArticleService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文章页面请求控制
 *
 * @author des
 * @since 2019-06-18
 */
@RequestMapping("/article")
@Controller
public class ArticleController {
    @Resource
    private IDDCArticleService articleService;
    @ResponseBody
    @RequestMapping("/list")
    public ResponseModel<List<DDCArticle>> displayAllArticle() {
        List<DDCArticle> articleList=articleService.getAllArticle();
        return ResponseHelper.buildResponseModel(articleList);
    }

    @RequestMapping("/add")
    public String addArticle( @RequestParam(value = "articletitle",required = false) String title,
                                            @RequestParam(value = "articletitle2",required = false) String shortTitle,
                                            @RequestParam(value = "articlecolumn",required = false) String classify,
                                            @RequestParam(value = "articletype",required = false) String type,
                                            @RequestParam(value = "articlesort",required = false) Integer sortValue,
                                            @RequestParam(value = "keywords",required = false) String keyWord,
                                            @RequestParam(value = "abstract",required = false) String abstracts,
                                            @RequestParam(value = "author",required = false) String writer,
                                            @RequestParam(value = "sources",required = false) String resource,
                                            @RequestParam(value = "content",required = false) String content){
        DDCArticle article=new DDCArticle(title,shortTitle,classify,type,sortValue,keyWord,abstracts,writer,resource,content);
        articleService.addArticle(article);
        return "article-list";
//        String msg="添加成功！";
//        return ResponseHelper.buildResponseModel(msg);
    }

    @RequestMapping("/del")
    public String delArticle(@RequestParam(value = "id",required = false) String aid)throws Exception {
        articleService.delArticleByID(aid);
        return "article-list";
    }

    @RequestMapping("/upDisplay")
    public String dispayArticle(HttpServletRequest request,@RequestParam(value = "id",required = false) long aid){
        System.out.println(aid);
        DDCArticle article=articleService.getArticleByID(aid);
        request.setAttribute("article",article);
        return "article-update";
    }

    @RequestMapping("/update")
    public String updateArticle(
                    @RequestParam(value = "id",required = false) long id,
                    @RequestParam(value = "articletitle",required = false) String title,
                    @RequestParam(value = "articletitle2",required = false) String shortTitle,
                    @RequestParam(value = "articlecolumn",required = false) String classify,
                    @RequestParam(value = "articletype",required = false) String type,
                    @RequestParam(value = "articlesort",required = false) Integer sortValue,
                    @RequestParam(value = "keywords",required = false) String keyWord,
                    @RequestParam(value = "abstract",required = false) String abstracts,
                    @RequestParam(value = "author",required = false) String writer,
                    @RequestParam(value = "sources",required = false) String resource,
                    @RequestParam(value = "content",required = false) String content)throws Exception {
        DDCArticle article=new DDCArticle(id,title,shortTitle,classify,type,sortValue,keyWord,abstracts,writer,resource,content);
        articleService.updateArticle(article);
        return "article-list";
//        String msg="修改成功！";
//        return ResponseHelper.buildResponseModel(msg);
    }

    @RequestMapping("/batchDel")
    public void batchDeletes(HttpServletRequest request, @RequestParam(value="delItems") String delItems) {
        String[] strs = delItems.split(",");
        for (int i = 0; i < strs.length; i++) {
            String aid = strs[i];
            articleService.delArticleByID(aid);;
        }
    }

//    @RequestMapping("article-update")
//    public void updateArticle(@RequestParam(value = "id",required = false) String aid)throws Exception {
//        articleService.delAtricleByID(aid);
//    }
}
