package com.xike.blog.dao;

import com.xike.blog.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectByCateName(String cateName);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    int updateByCateName(String oldName, String newName);
}