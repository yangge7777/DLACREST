﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //保存成功的提示信息
            function showResult() {

                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }

            //自动查询账务账号
            function searchAccounts(txtObj) {
                //document.getElementById("a1").innerHTML = txtObj.value;
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.jsp" class="role_off"></a></li>
                <li><a href="../admin/admin_list.jsp" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="service_list.jsp" class="service_on"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.jsp" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <!--保存操作的提示信息-->
            <div id="save_result_info" class="save_fail">保存失败！192.168.0.23服务器上已经开通过 OS 账号 “mary”。</div>
            <form  class="main_form">
                <!--内容项-->
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" value="查询出的结果写入账务账号" class="width180" />
                    <input type="button" value="查询账务账号" class="btn_search_large" />
                    <span class="required" id="iderror" style="display:none">*没有此身份证 请重新输入</span>

                </div>
                <div class="text_info clearfix"><span>账务账号：</span></div>
                <div class="input_info">
                    <input type="text" value="" id="accountname" name="ACCOUNT_ID" onkeyup="searchAccounts(this);" />
                    <span class="required"  style="display:none" id="accountnumbererror">*必填</span>

                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info">
                    <select id="select">
                        <c:forEach var="costname" items="${costselect}">
                            <option  value="${costname.cost_id}" >${costname.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <input type="text" name="UNIX_HOST" value=""  />
                    <span class="required">*</span>
                    <div class="validate_msg_long">15 长度，符合IP的地址规范</div>
                </div>                   
                <div class="text_info clearfix"><span>登录 OS 账号：</span></div>
                <div class="input_info">
                    <input type="text" name="OS_USERNAME" value="创建即启用，状态为开通"  />
                    <span class="required">*</span>
                    <div class="validate_msg_long">8长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input type="password" name="LOGIN_PASSWD" />
                    <span class="required">*</span>
                    <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input type="password"  />
                    <span  class="required">*</span>
                    <div class="validate_msg_long">两次密码必须相同</div>
                </div>     
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="subm();" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
                <input type="hidden" name="COST_ID" value="" id="COST_ID">

            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)云科技有限公司 </span>

            <script type="text/javascript" language="JavaScript">

                $(".btn_search_large").click(function () {
                    $("#iderror").attr("style","display:none")

                     var idcard_no =  $(".width180").val()

                    $.ajax({
                        type:"post",
                        url:"rilegou.do",
                        dataType:"json",
                        data:{"idcard_no":idcard_no},
                        success:function (data) {

                            rilegou(data)
                        },
                        error:function () {
                            alert("失败")
                        }


                    })

                })
            function rilegou(data) {
                if (data.code){
                    $("#accountname").attr("value",data.data.id)
                }else {
                    $("#iderror").show()
                }
            }
            function subm() {
                var COST_ID=$("#select").val()
                $("#COST_ID").val(COST_ID)
                $.ajax({
                    type:"post",
                    url:"insert_service.do",
                    dataType:"json",
                    data:$(".main_form").serialize(),
                    success:function (data) {
                       go(data)
                    },
                    error:function () {
                        alert("失败啦")
                    }
                })
            }
            function go(data) {
                if (data.flag){
                    window.location.href="toservice_list.do"
                }else {
                    window.location.href="toservice_add.do"
                }
            }


            </script>

        </div>
    </body>
</html>
