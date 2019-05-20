package com.study.shy.pipi.bean.event;

public class UserBean {

    String name;
    String account;
    String icon;

    public UserBean(String account,String name,String icon){
        this.account = account;
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
