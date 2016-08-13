package com.xy.xsql.orm.test.bean;

import com.xy.xsql.orm.annotation.*;

/**
 * TEST
 * Created by xiaoyao9184 on 2016/6/26.
 */
@EntityTable(name = "b_user_type",otherName = "ut")
public class UserType {

    @EntityColumnKey
    @EntityColumn(name = "id")
    private String id;

    @EntityColumnParam
    @EntityColumn(name = "name")
    private String name;

    @EntityColumnParam
    @EntityColumn(name = "code")
    private String code;

    @EntityColumnStatus
    @EntityColumn(name = "status")
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
