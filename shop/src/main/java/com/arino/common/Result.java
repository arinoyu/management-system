package com.arino.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

    private Object data;
    private Meta meta;

    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.setData(data);
        result.setMeta(new Meta(msg, 200));
        return result;
    }

    public static Result success(String msg, Object data, int status) {
        Result result = new Result();
        result.setData(data);
        result.setMeta(new Meta(msg, status));
        return result;
    }

    public static Result fail(String msg, int status) {
        Result result = new Result();
        result.setData(null);
        result.setMeta(new Meta(msg, status));
        return result;
    }

}
