package com.study.shy.pipi.eventbean;

public class SubBean {
    //订阅的分类名
    String categoryName;
    String categoryId;
    boolean isSub;

    public SubBean(String name,String id,boolean isSub){
        categoryId = id;
        categoryName = name;
        this.isSub = isSub;
    }

    @Override
    public String toString() {
        return "SubBean{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", isSub=" + isSub +
                '}';
    }

    public boolean isSub() {
        return isSub;
    }

    public void setSub(boolean sub) {
        isSub = sub;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
