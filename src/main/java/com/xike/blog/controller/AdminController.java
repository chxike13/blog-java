package com.xike.blog.controller;

import com.xike.blog.dao.AdminMapper;
import com.xike.blog.dao.ArticleMapper;
import com.xike.blog.dao.CateMapper;
import com.xike.blog.dao.CateViewMapper;
import com.xike.blog.model.Admin;
import com.xike.blog.model.Article;
import com.xike.blog.model.Cate;
import com.xike.blog.model.CateView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private CateMapper cateMapper;
    @Resource
    private CateViewMapper cateViewMapper;
    @Resource
    private AdminMapper adminMapper;

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request, RedirectAttributes attributes){
        if (request.getMethod().equals("GET")){
            return "login";
        }
        if (request.getMethod().equals("POST")){
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            Admin admin = adminMapper.selectByUserNameAndPassword(userName, password);
            if (admin != null){
                HttpSession session = request.getSession();
                session.setAttribute("userName", userName);
                return "redirect:/admin/index";
            }else {
                attributes.addFlashAttribute("error", "账号或密码错误!");
                return "redirect:/admin/login";
            }
        }
        return "e";
    }
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("mark", "index");
        return "admin";
    }

    @RequestMapping("/articleList")
    public String getArticleList(Model model){
        List<Article> articles = articleMapper.selectAll();
        model.addAttribute("articles", articles);
        model.addAttribute("mark", "articleList");
        return "admin";
    }

    @RequestMapping("/addArticle")
    public  String addArticle(Model model, HttpServletRequest request, RedirectAttributes attributes){
        if (request.getMethod().equals("GET")){
            List<Cate> cates = cateMapper.selectAll();
            model.addAttribute("cates", cates);
            model.addAttribute("mark","addArticle");
            return "admin";
        }
        if (request.getMethod().equals("POST")){
            Article article = new Article(
                    request.getParameter("title"),
                    request.getParameter("description"),
                    request.getParameter("cateName"),
                    request.getParameter("content")
            );
            int ret = articleMapper.insert(article);
            System.out.println(ret);
            attributes.addFlashAttribute("success", "添加成功!");
            return "redirect:/admin/articleList";
        }
        return "e";
    }

    @RequestMapping("/modArticle")
    public String modArticle(Model model, @RequestParam Integer id, HttpServletRequest request, RedirectAttributes attributes){
        if (request.getMethod().equals("GET")){
            Article article = articleMapper.selectByPrimaryKey(id);
            List<Cate> cates = cateMapper.selectAll();
            model.addAttribute("article", article);
            model.addAttribute("cates", cates);
            model.addAttribute("mark", "modArticle");
            return "admin";
        }
        if (request.getMethod().equals("POST")){
            Article article = new Article(
                    request.getParameter("title"),
                    request.getParameter("description"),
                    request.getParameter("cateName"),
                    request.getParameter("content")
            );
            Integer articleId = Integer.parseInt(request.getParameter("id"));
            System.out.println(articleId);
            article.setId(articleId);
            articleMapper.updateByPrimaryKey(article);
            attributes.addFlashAttribute("success", "修改成功!");
            return "redirect:/admin/articleList";
        }
        return "e";
    }

    @RequestMapping("/deleteArticle")
    public String deleteArticle(@RequestParam Integer id,RedirectAttributes attributes){
        articleMapper.deleteByPrimaryKey(id);
        attributes.addFlashAttribute("success", "删除成功！");
        return "redirect:/admin/articleList";
    }

    @RequestMapping("/cateList")
    public String getCateList(Model model){
        List<CateView> cateViews = cateViewMapper.selectAll();
        model.addAttribute("cateViews", cateViews);
        model.addAttribute("mark", "cateList");
        return "admin";
    }

    @RequestMapping("/addCate")
    public  String addCate(Model model, HttpServletRequest request, RedirectAttributes attributes){
        if (request.getMethod().equals("GET")){
            model.addAttribute("mark","addCate");
            return "admin";
        }
        if (request.getMethod().equals("POST")){
            Cate cate = new Cate(request.getParameter("cateName"));
            int ret = cateMapper.insert(cate);
            System.out.println(ret);
            attributes.addFlashAttribute("success", "添加成功!");
            return "redirect:/admin/cateList";
        }
        return "e";
    }

    @RequestMapping("/modCate")
    public String modCate(Model model, @RequestParam String cateName, HttpServletRequest request, RedirectAttributes attributes){
        if (request.getMethod().equals("GET")){
            model.addAttribute("cateName", cateName);
            model.addAttribute("mark", "modCate");
            return "admin";
        }
        if (request.getMethod().equals("POST")){
            cateMapper.updateByCateName(request.getParameter("oldName"), request.getParameter("cateName"));
            articleMapper.updateByCateName(request.getParameter("oldName"), request.getParameter("cateName"));
            attributes.addFlashAttribute("success", "修改成功!");
            return "redirect:/admin/cateList";
        }
        return "e";
    }

    @RequestMapping("/deleteCate")
    public String deleteCate(@RequestParam String cateName,RedirectAttributes attributes){
        cateMapper.deleteByCateName(cateName);
        attributes.addFlashAttribute("success", "删除成功！");
        return "redirect:/admin/cateList";
    }
}
