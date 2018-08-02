package com.yang.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.utils.CurrentTime;
import com.yang.admin.bean.Admin;
import com.yang.admin.bean.AdminSearch;
import com.yang.admin.bean.AdminShow;
import com.yang.admin.dao.AdminMapper;
import com.yang.role.bean.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
@Service("adminserviceimpl")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper mapper;

    @Override
    public List<Role> role_infoAll() {
        return mapper.role_infoAll();
    }

    @Override
    public boolean insertAdmininfo(Admin admin) {
        admin.setImpower_date(CurrentTime.currentTime());
        try {
            mapper.insertAdmininfo(admin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean insertAdmin_role(String admin_name, String role_id) {
        try {
            mapper.insertAdmin_role(admin_name, role_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public PageInfo<AdminShow> adminshowpage(int currentPage, int everyPageDatanum) {
        PageHelper.startPage(currentPage, everyPageDatanum);
        List<AdminShow> adminList = mapper.admin_infoAll();
        PageInfo<AdminShow> page = new PageInfo<>(adminList);
        for (AdminShow adminShow : page.getList()) {
            adminShow.setRoleList(mapper.role_Allbyadminid(adminShow.getAdmin_id()));
        }
        return page;
    }

    @Override
    public PageInfo<AdminShow> searchAdmin(AdminSearch adminSearch, int currentPage, int everyPageDatanum) {
        if ("全部".equals(adminSearch.getModule_id())) {
            adminSearch.setModule_id("");
        }
        Set<String> set = new HashSet<>();
        List<AdminShow> adminList = new ArrayList<>();
        String module_id = adminSearch.getModule_id();
        String name = adminSearch.getName();
        if (module_id != null && !"".equals(module_id) && name != null && !"".equals(name)) {
            List<AdminShow> a = mapper.a(name);
            List<AdminShow> b = mapper.b(module_id);
            for (AdminShow adminShow : a) {
                for (AdminShow show : b) {
                    if (show.getAdmin_id().equals(adminShow.getAdmin_id())) {
                        set.add(show.getAdmin_id());
                    }
                }
            }
            List<String> list = new ArrayList<>(set);
            PageHelper.startPage(currentPage, everyPageDatanum);
            List<AdminShow> adminList1 = mapper.c(list);

            PageInfo<AdminShow> page = new PageInfo<>(adminList1);
            for (AdminShow adminShow : page.getList()) {
                adminShow.setRoleList(mapper.role_Allbyadminid(adminShow.getAdmin_id()));
            }
            return page;

        } else if ((module_id != null && !"".equals(module_id)) && ("".equals(name) || name == null)) {
            PageHelper.startPage(currentPage, everyPageDatanum);
            List<AdminShow> b = mapper.b(module_id);
            PageInfo<AdminShow> page = new PageInfo<>(b);
            for (AdminShow adminShow : page.getList()) {
                adminShow.setRoleList(mapper.role_Allbyadminid(adminShow.getAdmin_id()));
            }
            return page;
        } else if ((module_id == null || "".equals(module_id)) && (!"".equals(name) && name != null)) {
            PageHelper.startPage(currentPage, everyPageDatanum);
            List<AdminShow> a = mapper.a(name);
            PageInfo<AdminShow> page = new PageInfo<>(a);
            for (AdminShow adminShow : page.getList()) {
                adminShow.setRoleList(mapper.role_Allbyadminid(adminShow.getAdmin_id()));
            }
            return page;
        } else {
            PageHelper.startPage(currentPage, everyPageDatanum);
            List<AdminShow> a = mapper.admin_infoAll();
            PageInfo<AdminShow> page = new PageInfo<>(a);
            for (AdminShow adminShow : page.getList()) {
                adminShow.setRoleList(mapper.role_Allbyadminid(adminShow.getAdmin_id()));
            }
            return page;
        }


    }

    @Override
    public AdminShow thisAdmin(String admin_id) {
        AdminShow adminShow = mapper.thisAdmin(admin_id);
        adminShow.setRoleList(  mapper.role_Allbyadminid(admin_id));

        return adminShow;
    }

    @Override
    public boolean updateAdmin_role(AdminShow adminShow,List<String> role_idList) {
        try {
            mapper.updateAdmin(adminShow);
            mapper.deleteAdmin_roleByAdminId(adminShow.getAdmin_id());
            for (String s : role_idList) {
                mapper.insertAdmin_role(adminShow.getAdmin_name(),s);
            }
            return true;
        }catch (Exception e){
            return false;
        }


    }


}
