package com.xike.blog.model;

public class Cate {
    private Integer id;

    private String catename;

    public Cate() {
    }

    public Cate(String catename) {
        this.catename = catename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename == null ? null : catename.trim();
    }
}