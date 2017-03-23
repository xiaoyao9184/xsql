package com.xy.xsql.entitytest.bean;


import com.xy.xsql.entity.api.annotation.*;

import java.util.Date;

/**
 * Created by xiaoyao9184 on 2016/11/28.
 */
@ETable(name = "b_user_log", aliasName = "ulog")
public class UserLog {

    @EKey
    @EColumn(name = "id")
    private String id;

    @ELink(value = User.class)
    @EColumn(name = "user_id")
    private String userID;

    @EParam
    @EColumn(name = "msg")
    private String msg;

    @EParam
    @EColumn(name = "time")
    private Date time;

    @EStatus()
    @EColumn(name = "status")
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
