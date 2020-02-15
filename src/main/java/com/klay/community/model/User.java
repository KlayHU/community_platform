package com.klay.community.model;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/2/15 19:40
 **/

public class User {
    private Integer id;

    private String account_id;

    private String name;

    private String token;

    private long gmt_create;

    private long gmt_modify;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public long getGmt_modify() {
        return gmt_modify;
    }

    public void setGmt_modify(long gmt_modify) {
        this.gmt_modify = gmt_modify;
    }
}
