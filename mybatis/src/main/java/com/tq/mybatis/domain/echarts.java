package com.tq.mybatis.domain;

public class echarts {
    String name;
    Integer num;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "echarts{" +
                "num='" + num + '\'' +
                ", name=" + name +
                '}';
    }
}
