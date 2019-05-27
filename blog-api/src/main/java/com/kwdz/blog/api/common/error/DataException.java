package com.kwdz.blog.api.common.error;

import lombok.Data;

import java.io.Serializable;
@Data
public class DataException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2007525058641283836L;

    private int code = 500;

    private String msg = "数据异常";

    public DataException(int code) {
        this.code = code;
    }

    public DataException(String msg) {
        this.msg = msg;
    }

    public DataException(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }
}