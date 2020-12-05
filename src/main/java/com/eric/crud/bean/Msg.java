package com.eric.crud.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg {

    private int code;
    private String msg;

    // client info
    private Map<String, Object> extend = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("Success!!");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(400);
        result.setMsg("Failed!!");
        return result;
    }

    public Msg add(String key, Object value) {
        extend.put(key, value);
        return this;
    }
}
