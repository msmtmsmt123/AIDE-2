package com.example.nianxin.gongjuxiang.db;

import org.litepal.crud.DataSupport;

/**
 * Created by nianxin on 2017/4/19.
 */

public class Danci extends DataSupport{
    private int id;
    private String name;
    private String text;
    private int yixue;
    private int yiji;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getYixue() {
        return yixue;
    }

    public void setYixue(int yixue) {
        this.yixue = yixue;
    }

    public int getYiji() {
        return yiji;
    }

    public void setYiji(int yiji) {
        this.yiji = yiji;
    }
}
