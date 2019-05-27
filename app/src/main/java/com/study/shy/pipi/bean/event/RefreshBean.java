package com.study.shy.pipi.bean.event;

public class RefreshBean {

    boolean isRefresh;

    public RefreshBean(boolean isRefresh){
        this.isRefresh = isRefresh;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }
}
