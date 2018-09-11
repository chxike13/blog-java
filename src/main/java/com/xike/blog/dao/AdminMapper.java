package com.xike.blog.dao;

import com.xike.blog.model.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer id);
    Admin selectByUserNameAndPassword(String userName, String password);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);
}