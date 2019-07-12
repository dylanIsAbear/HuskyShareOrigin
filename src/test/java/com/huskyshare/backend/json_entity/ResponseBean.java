package com.huskyshare.backend.json_entity;

import java.util.Date;

public class ResponseBean {
    private int status;
    private String msg;
    private Object data;
    private Date date;

    public ResponseBean(){};

    public ResponseBean(int status, String msg, Object data){
        this.date=new Date();
        this.data=data;
        this.msg=msg;
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
