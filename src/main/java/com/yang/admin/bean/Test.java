package com.yang.admin.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
public class Test {

    public static void main(String[] args) {
        try {
            Class admin = Class.forName("com.yang.admin.bean.Admin");
//            Class admin = Admin.class;
            //拿到 admin 的构造方法
            Constructor constructor = admin.getConstructor();
             Object o = constructor.newInstance();
            Field field = admin.getDeclaredField("admin_id");
            field.setAccessible(true);
            field.set(o,"1312");
            Method methodsetAdmin_id = admin.getMethod("setAdmin_id", String.class);
            methodsetAdmin_id.invoke(o, "2222");

            Field[] fields = admin.getFields();

            for (Field field1 : fields) {
                System.out.println(field1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static  Object newInstance (Class clazz){
        try {
            Object o  = clazz.newInstance();
            return  o ;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
