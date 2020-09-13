package com.tq.mybatis.mapper;

import com.tq.mybatis.domain.City;
import com.tq.mybatis.domain.echarts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EchartsMapper {

    @Select("SELECT * FROM echarts")
    public List<echarts>echarts_1();

    @Select("SELECT * FROM citymap WHERE cityname=#{cityname}")
    public City getCity(City city);
}
