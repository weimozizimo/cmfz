package com.baizhi.cmfz.entity;

import java.util.Date;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/9 20:00.
 */
public class Log {
    private String id;
    private String user;
    private Date time;
    private String resource;
    private String action;
    private String message;
    private String result;

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", time=" + time +
                ", resource='" + resource + '\'' +
                ", action='" + action + '\'' +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Log() {
    }

    public Log(String id, String user, Date time, String resource, String action, String message, String result) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.resource = resource;
        this.action = action;
        this.message = message;
        this.result = result;
    }
}
