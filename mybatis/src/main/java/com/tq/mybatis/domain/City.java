package com.tq.mybatis.domain;

public class City {
    String cityname;
    float weidu;
    float jingdu;
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getJingdu() {
        return jingdu;
    }

    public void setJingdu(float jingdu) {
        this.jingdu = jingdu;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public float getWeidu() {
        return weidu;
    }

    public void setWeidu(float weidu) {
        this.weidu = weidu;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityname='" + cityname + '\'' +
                ", weidu=" + weidu +
                ", jingdu=" + jingdu +
                '}';
    }
}
