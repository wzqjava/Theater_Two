package com.bw.movie.model;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:
 */
public class BaseResponse {
    private String message;
    private String status;
    public static final String SUCCESS = "0000";

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return SUCCESS.equals(status);
    }
}
