package com.yang.admin.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yang.admin.bean.Admin;
import com.yang.admin.bean.AdminSearch;
import com.yang.admin.bean.AdminShow;
import com.yang.role.bean.Role;

import java.util.List;

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
public interface AdminService {
    List<Role> role_infoAll();
    boolean insertAdmininfo(Admin admin);
    boolean insertAdmin_role(String admin_name,String role_id);
//    PageInfo<AdminShow> adminShowList();
    PageInfo<AdminShow> adminshowpage(int currentPage,int everyPageDatanum);

    PageInfo<AdminShow> searchAdmin(AdminSearch adminSearch,int currentPage, int everyPageDatanum);

    AdminShow thisAdmin(String admin_id);

    boolean updateAdmin_role(AdminShow adminShow,List<String> role_idList);

}
