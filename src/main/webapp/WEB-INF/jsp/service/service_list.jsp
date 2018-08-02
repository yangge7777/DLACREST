﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>

        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/easyui/themes/default/easyui.css" />
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/easyui/themes/icon.css" />

        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />

        <script language="javascript" type="text/javascript">
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //删除
            function deleteAccount() {
                var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通或暂停
            function setState() {
                var r = window.confirm("确定要开通此业务账号吗？");
                document.getElementById("operate_result_info").style.display = "block";
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
            <form >
                <!--查询-->
                <div class="search_add">                        
                    <div>OS 账号：<input type="text" name="OS_USERNAME" value="" class="width100 text_search" /></div>
                    <div>服务器 IP：<input type="text" name="UNIX_HOST" value="" class="width100 text_search" /></div>
                    <div>身份证：<input type="text"  name="idcard_no" value="" class="text_search" /></div>
                    <div>状态：
                        <select class="select_search">
                            <option selected="selected" value="">全部</option>
                            <option value="1">开通</option>
                            <option value="2">暂停</option>
                            <option value="3">删除</option>
                        </select>
                    </div>
                    <input type="hidden" id="status2" name="status" value="">
                    <div><input type="button" value="搜索" class="btn_search" onclick="bussearch()" /></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='<c:url value="/bus/toservice_add.do" />';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>

                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <thead>
                    <tr>
                        <th class="width50">业务ID</th>
                        <th class="width70">账务账号ID</th>
                        <th class="width150">身份证</th>
                        <th class="width70">姓名</th>
                        <th>OS 账号</th>
                        <th class="width50">状态</th>
                        <th class="width100">服务器 IP</th>
                        <th class="width100">资费</th>                                                        
                        <th class="width200"></th>
                    </tr>
                        </thead>

                        <div id="tecc"></div>

                        <c:forEach var="serviceshow" items="${serviceAll.list}" >
                            <tr class="ccc">
                                <td><a href="service_detail.html" title="查看明细">${serviceshow.SERVICEID}</a></td>
                                <td>${serviceshow.id}</td>
                                <td>${serviceshow.idcard_no}</td>
                                <td>${serviceshow.real_name}</td>
                                <td>${serviceshow.OS_USERNAME}</td>
                                <c:if test="${serviceshow.STATUS==1}">
                                    <td>开通</td>
                                </c:if>
                                <c:if test="${serviceshow.STATUS==2}">
                                    <td>暂停</td>
                                </c:if>
                                <c:if test="${serviceshow.STATUS==3}">
                                    <td>删除</td>
                                </c:if>
                                <td>${serviceshow.UNIX_HOST}</td>
                                <td>
                                    <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${serviceshow.name}</a>
                                    <!--浮动的详细信息-->
                                    <div class="detail_info">
                                       ${serviceshow.descr}
                                    </div>
                                </td>

                                <c:if test="${serviceshow.STATUS==1}">
                                    <td class="td_modi">
                                        <input type="button" value="暂停" class="btn_pause" onclick="setState();" />
                                        <input type="button" value="修改" class="btn_modify" onclick="location.href='service_modi.html';" />
                                        <input type="button" value="删除" class="btn_delete" onclick="deleteAccount();" />
                                    </td>
                                </c:if>

                                <c:if test="${serviceshow.STATUS==2}">
                                    <td class="td_modi">
                                        <input type="button" value="开通" class="btn_pause" onclick="setState();" />
                                        <input type="button" value="修改" class="btn_modify" onclick="location.href='service_modi.html';" />
                                        <input type="button" value="删除" class="btn_delete" onclick="deleteAccount();" />
                                    </td>
                                </c:if>

                                <c:if test="${serviceshow.STATUS==3}">
                                    <td class="td_modi">

                                    </td>
                                </c:if>
                            </tr>


                        </c:forEach>

                    </table>
                </div>

                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>

                <!--分页-->

                <div id="pages">
                    <a href="#">首页</a>
        	        <a href="#">上一页</a>
                  <c:forEach begin="1" end="${serviceAll.pages}" var="page">

                     <a  onclick='go(this.text)' >${page}</a>

                  </c:forEach>
                    <a href="#">下一页</a>
                    <a href="#">末页</a>
                </div>

            </form>

        </div>

        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)云科技有限公司 </p>
        </div>
    <script type="text/javascript" language="JavaScript">
    function go(currentpage) {
//        !!!!!!!!!!!


    }


    function bussearch() {
        var status = "";
         status =$(".select_search").val()

            alert(status)
        $("#status2").attr("value",status)
        $.ajax({
            type:"post",
            dataType:"json",
            data:$("form").serialize(),
            url:"searchService.do",
            success:function (data) {
              abc(data)
            },error:function () {
                alert("失败")
            }
        })
    }
    function abc(data) {
        $("#tecc").parent().html("")






      $.each( data.serviceAll.list,function (i,item) {

          alert(item.serviceid+item.id+item.idcard_no+item.real_name+"...")
      })



    }




    </script>


    </body>
</html>
