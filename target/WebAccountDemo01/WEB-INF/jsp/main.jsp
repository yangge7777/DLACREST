﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/styles/global_color.css" />
    </head>
    <body class="index">
        <!--导航区域开始-->
        <div id="index_navi">
            <ul id="menu">
                <li><a href="main.jsp" class="index_on"></a></li>
                <li><a href="<%=request.getContextPath()%>/role/torole_list.do" class="role_off"></a></li>
                <li><a href="<%=request.getContextPath()%>/admin/toadmin_list.do" class="admin_off"></a></li>
                <li><a href="<%=request.getContextPath()%>/fee/feelist.do" class="fee_off"></a></li>
                <li><a href="<%=request.getContextPath()%>/account/accountlist.do?queryJson=queryJson" class="account_off"></a></li>
                <li><a href="<%= request.getContextPath()%>/bus/toservice_list.do" class="service_off"></a></li>
                <li><a href="bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="<%= request.getContextPath()%>/excel/abc.do" class="report_off"></a></li>
                <li><a href="user/user_info.jsp" class="information_off"></a></li>
                <li><a href="<%=request.getContextPath()%>/user/to_user_modi_pwd.do" class="password_off"></a></li>
            </ul>
        </div>
    </body>
</html>
