package com.yang.login.controller;

import com.yang.login.service.LoginService;
import com.yang.utils.ImageUtil;
import org.apache.ibatis.session.LocalCacheScope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginServiceImpl;

    private String code;
    public static String admin_account1;

    @RequestMapping("/login.do")
    public String login(String admin_account,HttpServletRequest request) {
     HttpSession session  =   request.getSession();
        session.setAttribute("admin", admin_account);
        System.out.println("我勒个去"+admin_account);
//        int a = 1 / 0;
        return "main";
    }


    @RequestMapping(value = "choose.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> choose(@RequestParam("admin_account") String account) {

        Map<String, Object> map = new HashMap<>();
        boolean flag = loginServiceImpl.select_AdminByaccount(account);
        map.put("msg", flag);
        return map;

    }


    @RequestMapping(value = "/getPicStream.do")
    public void valicode(HttpServletRequest req, HttpSession session,HttpServletResponse response
    ) throws Exception{
//第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = ImageUtil.createImage();
        //将验证码存入缓存，方便待会比对
        String tokenId= req.getHeader("token_id");
        this.code =(String) objs[0];

//将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
//        ByteArrayOutputStream os = new ByteArrayOutputStream();//新建流。
        ImageIO.write(image, "png", os);
    }

    @RequestMapping("/loginChoose.do")
    @ResponseBody
    public Map<String,Object> loginChoose(
            @RequestParam("admin_account") String admin_account,
            @RequestParam("admin_pswd") String admin_pswd,
            @RequestParam("admin_code") String admin_code
    ){

        //md5

    admin_pswd =DigestUtils.md5DigestAsHex(admin_pswd.getBytes());
        System.out.println(admin_pswd);
        Map<String, Object> map = new HashMap<>();

        String ccode = code.toLowerCase();
        String aadmin_code = admin_code.toLowerCase();
        boolean flagcode = ccode.equals(aadmin_code);
        boolean flaglogin = loginServiceImpl.select_AdminLogin(admin_account, admin_pswd);
     boolean flag= loginServiceImpl.login(flagcode, flaglogin);


        map.put("cc", flag);
        map.put("code", flagcode);
        map.put("login", flaglogin);
        map.put("admin_account", admin_account);
        return map;
    }




}