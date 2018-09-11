package com.xike.blog.controller;

import com.xike.blog.dao.ArticleMapper;
import com.xike.blog.dao.CateMapper;
import com.xike.blog.dao.CateViewMapper;
import com.xike.blog.model.Article;
import com.xike.blog.model.CateView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/blog")
@Controller
public class BlogController {
    @Resource
    ArticleMapper articleMapper;
    @Resource
    CateMapper cateMapper;
    @Resource
    CateViewMapper cateViewMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @ResponseBody
    @RequestMapping("/{ModelType}")
    public String index(Model model,
                        @PathVariable String ModelType,
                        @RequestParam(required = false) Integer id,
                        @RequestParam(required = false) String cateName){
        logger.info("Controller index start...........");
        List<CateView> cateViews = cateViewMapper.selectAll();
        model.addAttribute("cateViews", cateViews);
        if (ModelType.equals("index")){
            List<Article> articles = articleMapper.selectAll();
            model.addAttribute("articles", articles);
            return "index";
        }
        if (ModelType.equals("content")){
            Article article = articleMapper.selectByPrimaryKey(id);
            model.addAttribute("article", article);
            return "content";
        }
        if (ModelType.equals("cate")){
            List<Article> articles = articleMapper.selectByCateName(cateName);
            model.addAttribute("cateName", cateName);
            model.addAttribute("articles", articles);
            return "cate";
        }
        return "e";
    }

}
