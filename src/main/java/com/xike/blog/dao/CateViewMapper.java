package com.xike.blog.dao;

import com.xike.blog.model.CateView;
import java.util.List;

public interface CateViewMapper {
    int insert(CateView record);

    List<CateView> selectAll();
}