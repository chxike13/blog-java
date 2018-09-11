package com.xike.blog.dao;

import com.xike.blog.model.CateView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CateViewMapper {
    int insert(CateView record);

    List<CateView> selectAll();
}