<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/lodash.min.js"></script>

    <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css"/>
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
        //重置密码
        function resetPwd() {
            alert("请至少选择一条数据！");
            //document.getElementById("operate_result_info").style.display = "block";
        }
        //删除
        function deleteAdmin() {
            var r = window.confirm("确定要删除此管理员吗？");
            document.getElementById("operate_result_info").style.display = "block";
        }
        //全选
        function selectAdmins(inputObj) {
            var inputArray = document.getElementById("datalist").getElementsByTagName("input");
            for (var i = 1; i < inputArray.length; i++) {
                if (inputArray[i].type == "checkbox") {
                    inputArray[i].checked = inputObj.checked;
                }
            }
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
        <li><a href="../role/role_list.html" class="role_off"></a></li>
        <li><a href="admin_list.jsp" class="admin_on"></a></li>
        <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
        <li><a href="../account/account_list.html" class="account_off"></a></li>
        <li><a href="../service/service_list.html" class="service_off"></a></li>
        <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
        <li><a href="../report/report_list.html" class="report_off"></a></li>
        <li><a href="../user/user_info.jsp" class="information_off"></a></li>
        <li><a href="../user/user_modi_pwd.jsp" class="password_off"></a></li>
    </ul>
</div>
<!--导航区域结束-->
<!--主要区域开始-->
<div id="main">
    <form action="<%=request.getContextPath()%>/admin/searchAdmin.do" method="post">
        <!--查询-->
        <div class="search_add">
            <div>
                模块：
                <select id="selModules" class="select_search">
                    <option>全部</option>
                    <c:forEach var="module" items="${modulelist}">
                        <option value="${module.module_id}">${module.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>角色：<input type="text" id="adminname" name="name" value="${adminSearch.name!=null?adminSearch.name:''}"
                           class="text_search width200"/></div>
            <div><input type="button" value="搜索" class="btn_search" onclick="Adminsearch()"/></div>
            <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();"/>
            <input type="button" value="增加" class="btn_add" onclick="location.href='toadmin_add.do';"/>
        </div>
        <!--删除和密码重置的操作提示-->
        <div id="operate_result_info" class="operate_fail">
            <img src="../images/close.png" onclick="this.parentNode.style.display='none';"/>
            <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
        </div>
        <!--数据区域：用表格展示数据-->
        <div id="data">
            <table id="datalist">
                <tr>
                    <th class="th_select_all">
                        <input type="checkbox" onclick="selectAdmins(this);"/>
                        <span>全选</span>
                    </th>
                    <th>管理员ID</th>
                    <th>姓名</th>
                    <th>登录名</th>
                    <th>电话</th>
                    <th>电子邮件</th>
                    <th>授权日期</th>
                    <th class="width100">拥有角色</th>
                    <th></th>
                </tr>
                <tr>
                    <c:forEach var="adminShow" items="${page.list}">
                    <td><input type="checkbox"/></td>
                    <td>${adminShow.admin_id}</td>
                    <td>${adminShow.admin_name}</td>
                    <td>${adminShow.admin_account}</td>
                    <td>${adminShow.admin_telephone}</td>
                    <td>${adminShow.admin_Eamil}</td>
                    <td>${adminShow.impower_date}</td>
                    <td>
                        <a class="summary" onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
                            <c:forEach var="role" items="${adminShow.roleList}">
                                   ${role.name}
                            </c:forEach>

                        </a>
                        <!--浮动的详细信息-->
                        <div class="detail_info">
                            <c:forEach var="role" items="${adminShow.roleList}">
                                ${role.name}
                            </c:forEach>
                        </div>
                    </td>
                    <td class="td_modi">
                        <input type="button" value="修改" class="btn_modify"
                               onclick="updateAdmin(${adminShow.admin_id})"/>
                        <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin();"/>
                    </td>
                </tr>
                <tr>
                    </c:forEach>
            </table>
        </div>
        <!--分页-->
        <div id="pages">
            <c:if test="${page.hasPreviousPage}">
                <a onclick="adminpage(${page.prePage})">上一页</a>
            </c:if>
            <c:if test="${!page.hasPreviousPage}">
                <a style="color: grey">上一页</a>
            </c:if>
            <c:forEach begin="1" end="${page.pages}" var="page">
                <a onclick="adminpage(${page})">${page}</a>
            </c:forEach>
            <c:if test="${page.hasNextPage}">
                <a onclick="adminpage(${page.nextPage})">下一页</a>
            </c:if>
            <c:if test="${!page.hasNextPage}">
                <a style="color: grey">下一页</a>
            </c:if>
            <input type="hidden" name="module_id" value="${adminSearch.module_id!=null?adminSearch.module_id:''}"
                   id="module_id">
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">
    <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
    <span>版权所有(C)云科技有限公司 </span>
</div>
<script type="text/javascript" language="JavaScript">
    function Adminsearch() {
        var module_id = ""
        module_id = $("#selModules").val();
        $("#module_id").val(module_id)
        $("form").submit()
    }
    function adminpage(currentPage) {
        var name = "";
        var module_id = "";
        name = "${adminSearch.name}"
        module_id = "${adminSearch.module_id}"
        window.location.href = "pageforadminlist.do?" +
                "module_id=" + module_id +
                "&name=" + name +
                "&currentPage=" + currentPage
    }
    function updateAdmin(admin_id) {
        var url = "";
        url="toadmin_modi.do";
//        $.post(url,{'admin_id' : admin_id})
        window.location.href=url+"?admin_id="+admin_id
    }



</script>
</body>
</html>
