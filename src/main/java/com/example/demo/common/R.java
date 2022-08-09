package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {

    String msg;
    Object data;

    public R(String msg) {
        this.msg = msg;
    }

    public static R success(Object o){
        return new R("success", o);
    }
    public static R success(){
        return new R("success");
    }

    public static R error(String errorMsg,Object o){
        return new R(errorMsg, o);
    }
    public static R error(String errorMsg){
        return new R(errorMsg);
    }
}
