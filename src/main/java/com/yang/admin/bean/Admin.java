package com.yang.admin.bean;

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
public class Admin {
    private String admin_id,admin_name,admin_account,admin_pswd,admin_telephone,admin_Eamil, impower_date;

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id='" + admin_id + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", admin_account='" + admin_account + '\'' +
                ", admin_pswd='" + admin_pswd + '\'' +
                ", admin_telephone='" + admin_telephone + '\'' +
                ", admin_Eamil='" + admin_Eamil + '\'' +
                ", impower_date='" + impower_date + '\'' +
                '}';
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_account() {
        return admin_account;
    }

    public void setAdmin_account(String admin_account) {
        this.admin_account = admin_account;
    }

    public String getAdmin_pswd() {
        return admin_pswd;
    }

    public void setAdmin_pswd(String admin_pswd) {
        this.admin_pswd = admin_pswd;
    }

    public String getAdmin_telephone() {
        return admin_telephone;
    }

    public void setAdmin_telephone(String admin_telephone) {
        this.admin_telephone = admin_telephone;
    }

    public String getAdmin_Eamil() {
        return admin_Eamil;
    }

    public void setAdmin_Eamil(String admin_Eamil) {
        this.admin_Eamil = admin_Eamil;
    }

    public String getImpower_date() {
        return impower_date;
    }

    public void setImpower_date(String impower_date) {
        this.impower_date = impower_date;
    }
}
