package com.yang.business.service;

import com.yang.utils.Page;
import com.yang.business.bean.Business;
import com.yang.business.bean.BusinessShow;
import com.yang.business.bean.SearchBusniess;
import com.yang.fee.bean.Cost;

import java.util.List;
import java.util.Map;

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
public interface BusinessService {
    //accountAll_byidcard_no
    Map<String, Object> accountAll_byidcard_no(String idcard_no);
    //costname_Bystatus1
    List<Cost> costname_Bystatus1();
    boolean insert_service(Business business);
    Page<BusinessShow> service_show();
    Page<BusinessShow> service_search(SearchBusniess searchBusniess,Page<BusinessShow> page);


}
