<%@page import="tv.samim.common.security.UserDetail"%>
<%@ page import="tv.samim.template.utility.PathUtility" %>
<%@ page language="java" contentType="text/html; UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String home;
    if (true)
        home = PathUtility.buildRedirectUrlToHomePage(request, response);
    else
        home = UserDetail.getCurrentUser(request).getHome() + "index.jsp";
    boolean is_auth = false;
    try {
        is_auth = UserDetail.isLoggin(request);
    } catch (Exception ex) {
        //Logger.e(ex);
    }
    if (is_auth == true) {
%>
<div class="collapse navbar-collapse" id="main-header">
    <ul class="main-menu nav navbar-nav main-header">
        <li class="nav">
            <a href="<%=home%>" style="padding: 17px 15px;">
                <i class="fa fa-home fa-lg"></i>
            </a>
        </li>
        <li class="nav">
            <a href="<s:url value="home" />">
                <s:message code="label.title" />
            </a>
        </li>

        <li class="nav">
            <a href="<s:url value="about"/>">
                <s:message code="about.title" />
            </a>
        </li>
    </ul>
</div>
<% } else { %>
<ul class="main-menu nav navbar-nav main-header">
    <li class="nav">
        <span>
            <s:message code="label.title" />
	    </span>
    </li>
</ul>
<% } %>