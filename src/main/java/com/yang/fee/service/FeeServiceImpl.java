package com.yang.fee.service;

import com.yang.fee.bean.Cost;
import com.yang.fee.dao.FeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 18/7/16.
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * My Dear Taoism's Friend .Please SitDown.
 */
@Service
public class FeeServiceImpl implements FeeService{
    @Resource
    private FeeMapper mapper;

    @Override
    public List<Cost> cost_All() {
     return   mapper.cost_All();
    }

    @Override
    public Cost cost_Byid(String id) {
        return  mapper.cost_Byid(id);
    }

    @Override
    public boolean cost_update(Cost cost) {
        switch (cost.getCost_type()){
            case "1": cost.setBase_cost(null);
                break;
            case "3":cost.setBase_duration(null);
                break;
        }
        try {
            mapper.cost_update(cost);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public void cost_deleteByid(String id) {
        mapper.cost_deleteByid(id);
    }

    @Override
    public boolean cost_insert(Cost cost) {
        switch (cost.getCost_type()){
            case "1": cost.setBase_cost(null);
                break;
            case "3":cost.setBase_duration(null);
                break;
        }
        cost.setStatus("1");
        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        cost.setCreatime(df.format(day));
        cost.getCreatime();
        if (mapper.cost_ByName(cost.getName())!=null){
            return false;
        }
        try {
            mapper.cost_insert(cost);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void cost_updateStatus(String id) {
        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         mapper.cost_updateStatus(id,df.format(day));
    }

}
