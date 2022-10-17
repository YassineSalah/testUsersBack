package com.example.machinestalk.model;



import java.util.List;
import java.util.Map;

public class Data {

    private Integer code;
    private Map meta;
    private List<UserMachinestalk> data;

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Data{" +
                "code=" + code +
                ", meta=" + meta +
                ", data=" + data +
                '}';
    }

    public Data() {
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map getMeta() {
        return meta;
    }

    public void setMeta(Map meta) {
        this.meta = meta;
    }

    public List<UserMachinestalk> getData() {
        return data;
    }

    public void setData(List<UserMachinestalk> data) {
        this.data = data;
    }
}
