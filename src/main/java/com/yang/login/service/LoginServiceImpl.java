package com.yang.login.service;

import com.yang.admin.bean.Admin;
import com.yang.login.dao.LoginMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 18/7/27.
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
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper mapper;


    @Override
    public boolean select_AdminByaccount(String admin_account) {
        return mapper.select_AdminByaccount(admin_account) != null;
    }

    @Override
    public boolean select_AdminLogin(String admin_account, String admin_pswd) {

        List<Admin> adminList = mapper.select_AdminLogin(admin_account, admin_pswd);
        return adminList.size() == 1;
    }

    @Override
    public boolean login(boolean codeflag, boolean loginflag) {
        if (codeflag && loginflag) {
            return true;
        } else {
            return false;
        }
    }
}
