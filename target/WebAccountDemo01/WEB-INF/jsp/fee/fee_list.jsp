﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath()%>/styles/global_color.css" />
        <script language="javascript" type="text/javascript">

            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startFee(aa) {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                if (r){
                    window.location.href="cost_updateStatus.do?method="+aa
                }else {
                    alert("不启用你点他干啥!!干啥!!!我还得给你写个else 烦不烦!!烦不烦!!啊?!!!烦不烦!!")
                }

            }
            //删除
            function deleteFee(aa) {
                var r = window.confirm("确定要删除此资费吗？");
                if(r){
                    window.location.href="cost_delete.do?method="+aa
//                    document.getElementById("operate_result_info").style.display = "block";
                }else {

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
                <li><a href="../role/role_list.jsp" class="role_off"></a></li>
                <li><a href="../admin/admin_list.jsp" class="admin_off"></a></li>
                <li><a href="<%= request.getContextPath()%>/fee/feelist.do" class="fee_on"></a></li>
                <li><a href="../account/account_list.jsp" class="account_off"></a></li>
                <li><a href="../service/service_list.jsp" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.jsp" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <!--<input type="button" value="月租" class="sort_asc" onclick="sort(this);" />-->
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='<c:url value="/fee/tofee_add.do"/> '" />
                </div>
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>
                <!--数据区域：用表格展示数据-->
                <div id="data">
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>
                    <c:forEach var="cost" items="${costAll}">
                        <tr>
                            <td>${cost.cost_id}</td>
                            <td><a href="<c:url value='/fee/tofee_detail.do?method=${cost.cost_id}'/> ">${cost.name}</a></td>
                            <td>${cost.base_duration} ${cost.base_duration ==null ? "" : "小时"}</td>
                            <td>${cost.base_cost}  ${cost.base_cost ==null ? "" : "元"}</td>
                            <td>${cost.unit_cost}  ${cost.unit_cost ==null ? "" : "元/小时"}</td>
                            <td>${cost.creatime}</td>
                            <td>${cost.startime}</td>
                            <c:if test="${cost.status==1}">
                                <td>暂停</td>
                            </c:if>
                            <c:if test="${cost.status==2}">
                                <td>开通</td>
                            </c:if>
                            <c:if test="${cost.status==1}">
                            <td  >
                                    <input type="button" value="启用" class="btn_start" onclick="startFee(${cost.cost_id});" />
                                    <input type="button" value="修改" class="btn_modify" onclick="location.href='<c:url value="/fee/tofee_modi.do?method=${cost.cost_id}"/> '" />
                                    <input type="button" value="删除" class="btn_delete" onclick="deleteFee(${cost.cost_id})"/>
                        <%--location.href='<c:url value="/fee/cost_delete.do?method=${cost.cost_id}--%>
                            </td>
                            </c:if>
                            <c:if test="${cost.status==2}">
                                <td>

                                </td>
                            </c:if>
                        </tr>


                    </c:forEach>

                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
        	        <a href="#">上一页</a>
                    <a href="#" class="current_page">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">下一页</a>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)云科技有限公司</p>
        </div>

    </body>
</html>
