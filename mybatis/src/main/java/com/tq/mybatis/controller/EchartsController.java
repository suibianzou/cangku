package com.tq.mybatis.controller;

import com.tq.mybatis.domain.echarts;
import com.tq.mybatis.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EchartsController {
    @Autowired
    EchartsService echartsService;
    @ResponseBody
    @PostMapping("/admin/getEcharts")
    public List<echarts> getEcharts(){
        try{
            System.out.println(echartsService.getEchartsNum());
            
            return echartsService.getEchartsNum();
        }catch (Exception e){
            return null;
        }
    }
}
