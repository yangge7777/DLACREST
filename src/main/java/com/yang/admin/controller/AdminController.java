package com.yang.admin.controller;

import com.yang.admin.bean.Admin;
import com.yang.admin.bean.AdminSearch;
import com.yang.admin.bean.AdminShow;
import com.yang.admin.service.AdminService;
import com.yang.login.controller.LoginController;
import com.yang.role.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dllo on 18/7/26.
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
@Controller
@RequestMapping("/admin")
public class AdminController {


    @Resource(name = "adminserviceimpl")
    private AdminService service;
    @Resource
    private RoleService roleServiceImpl;
    @RequestMapping("/toadmin_list.do")
    public ModelAndView toadmin_list(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin_list");
        modelAndView.addObject("page", service.adminshowpage(1,5));
        modelAndView.addObject("modulelist", roleServiceImpl.module_infoAll());
        return modelAndView;
    }

    @RequestMapping("/toadmin_add.do")
    public ModelAndView toadmin_add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin_add");
        modelAndView.addObject("rolelist",service.role_infoAll());
        return modelAndView;
    }

    @RequestMapping("/addAdmin.do")
    public ModelAndView addAdmin(Admin admin , @RequestParam("role_id")List<String> role_idList){
        //md5
        admin.setAdmin_pswd(DigestUtils.md5DigestAsHex(admin.getAdmin_pswd().getBytes()));

        boolean flag = service.insertAdmininfo(admin);
        for (String s : role_idList) {
            service.insertAdmin_role(admin.getAdmin_name(), s);
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin_list");
        modelAndView.addObject("page", service.adminshowpage(1,5));
        modelAndView.addObject("modulelist", roleServiceImpl.module_infoAll());
        return modelAndView;
    }



    @RequestMapping("/pageforadminlist.do")
    public ModelAndView pageforadminlist(AdminSearch adminSearch,String currentPage){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin_list");
        modelAndView.addObject("page", service.searchAdmin(adminSearch,Integer.valueOf(currentPage),5));
        modelAndView.addObject("adminSearch", adminSearch);
        modelAndView.addObject("modulelist", roleServiceImpl.module_infoAll());
        return modelAndView;
    }

    @RequestMapping("/searchAdmin.do")
    public ModelAndView searchAdmin(AdminSearch adminSearch) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin_list");
        modelAndView.addObject("page",service.searchAdmin(adminSearch,1,5));
        modelAndView.addObject("adminSearch", adminSearch);
        modelAndView.addObject("modulelist", roleServiceImpl.module_infoAll());

        return modelAndView;
    }

    @RequestMapping(value = "/toadmin_modi.do")
    public ModelAndView toadmin_modi(String admin_id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin_modi");
        modelAndView.addObject("rolelist",service.role_infoAll());
         modelAndView.addObject("thisAdminShow",service.thisAdmin(admin_id));

        return modelAndView;
    }

    @RequestMapping("/updateAdmin.do")
    public ModelAndView updateAdmin(AdminShow adminShow ,@RequestParam("role_id")List<String >role_idList){
        service.updateAdmin_role(adminShow, role_idList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin_list");
        modelAndView.addObject("page", service.adminshowpage(1,5));
        modelAndView.addObject("modulelist", roleServiceImpl.module_infoAll());
        return modelAndView;

    }
}
