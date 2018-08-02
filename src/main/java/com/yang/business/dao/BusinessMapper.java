package com.yang.business.dao;

import com.yang.utils.Page;
import com.yang.account.bean.Account;
import com.yang.business.bean.Business;
import com.yang.business.bean.BusinessShow;
import com.yang.business.bean.SearchBusniess;
import com.yang.fee.bean.Cost;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dllo on 18/7/21.
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
@Repository
public interface BusinessMapper {
    //accountAll_byidcard_no
    Account accountAll_byidcard_no(String idcard_no);
    //costname_Bystatus1
   List<Cost> costname_Bystatus1();

    void insert_service(Business business);
    //servicelist
    List<BusinessShow> service_show();
    //servicecount
    int service_count();


    //searchList
    List<BusinessShow> searchlist (@Param("b") SearchBusniess searchBusniess, @Param("p") Page<BusinessShow> page);
    //searchcount
    int search_service_count(SearchBusniess searchBusniess);


}
