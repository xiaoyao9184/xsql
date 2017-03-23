package com.xy.xsql.orm.test.bean;

import com.xy.xsql.entity.annotation.*;

/**
 * TEST
 * Created by xiaoyao9184 on 2016/6/26.
 */
@ETable(name = "b_user", aliasName = "u")
public class User {

    @EKey
    @EColumn(name = "id")
    private String id;

    @EParam
    @EColumn(name = "name")
    private String name;

    @EParam
    @EColumn(name = "code")
    private String code;

    @ELink(value = UserType.class)
    @EParam
    @EColumn(name = "type")
    private String typeID;

    @EStatus()
    @EColumn(name = "status")
    private int status;

    @EOrder()
    @EColumn(name = "time")
    private int time;

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

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
