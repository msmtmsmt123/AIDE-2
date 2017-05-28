package com.example.nianxin.gongjuxiang.db;

/**
 * Created by nianxin on 2017/4/2.
 */

public class ShuJu {
    private String shuju;
    private String _id;
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getShuju() {
        return shuju;
    }
    public void setShuju(String shuju) {
        this.shuju = shuju;
    }

    public String toString() {
        return shuju;
    }
    public ShuJu(String _id, String shuju) {
        super();
        this._id = _id;
        this.shuju=shuju;
    }
}
