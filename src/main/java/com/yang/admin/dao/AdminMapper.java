package com.yang.admin.dao;

import com.yang.admin.bean.Admin;
import com.yang.admin.bean.AdminSearch;
import com.yang.admin.bean.AdminShow;
import com.yang.role.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
@Repository
public interface AdminMapper {
    List<Role> role_infoAll();

    void insertAdmininfo(Admin admin);
    void insertAdmin_role(@Param("admin_name")String admin_name,@Param("role_id")String role_id);

    List<AdminShow> admin_infoAll();
    List<Role> role_Allbyadminid(String admin_id);

    List<AdminShow> searchAdmin(AdminSearch adminSearch);

    List<AdminShow> a(String name);

    List<AdminShow> b(String module_id);

   List<AdminShow> c(List<String> list);

    AdminShow thisAdmin(String admin_id);


    void updateAdmin(AdminShow adminShow);

    void deleteAdmin_roleByAdminId(String admin_id);





}
