package com.xy.xsql.test.entity.bean;


import com.xy.xsql.entity.api.annotation.*;

/**
 * TEST
 * Created by xiaoyao9184 on 2016/6/26.
 */
@ETable(name = "b_user_type", aliasName = "ut")
public class UserType {

    @EKey
    @EColumn(name = "id")
    private String id;

    @EParam
    @EColumn(name = "name")
    private String name;

    @EParam
    @EColumn(name = "code")
    private String code;

    @EStatus
    @EColumn(name = "status")
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
