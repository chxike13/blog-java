package com.xike.blog.dao;

import com.xike.blog.model.Cate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CateMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByCateName(String cateName);

    int insert(Cate record);

    Cate selectByPrimaryKey(Integer id);

    List<Cate> selectAll();

    int updateByPrimaryKey(Cate record);
    int updateByCateName(String oldName, String newName);
}