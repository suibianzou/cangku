package com.tq.mybatis.service;

import com.tq.mybatis.domain.echarts;
import com.tq.mybatis.mapper.EchartsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsService {

    @Autowired
    EchartsMapper echartsMapper;

    public List<echarts> getEchartsNum(){
        return echartsMapper.echarts_1();
    }
}
