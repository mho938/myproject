<%@page import="tv.samim.common.security.CurrentUser"%>
<%@page import="java.util.Locale"%>
<%@page import="tv.samim.common.security.UserDetail"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page import="tv.samim.template.utility.PathUtility" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<s:url value="/" var="rootUrl" />
<li style="font-size: 16px;" class="text-center">
    <%  boolean is_auth = UserDetail.isLoggin(request);
        if (is_auth == true) {
            String name = "";
            CurrentUser currentUser = UserDetail.getCurrentUser(request);
            Object un = currentUser.getUsername();
            if (un != null) {
                name = un.toString();
            }
            if (name.equals("anonymousUser")) {
                Locale locale = LocaleContextHolder.getLocale();
                name = ResourceBundle.getBundle("text", locale).getString(("anonymousUser"));
                name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            }
            String ip = request.getServerName();

            String path = URLEncoder.encode(
                    "http://" + ip + ":" + request.getServerPort()
                            + request.getRequestURI(), "UTF-8");
            String str = path;


                str = PathUtility.buildRedirectUrlToLoginPage(request, response);

    %>
    <div>
        <span style="line-height: 2em;"><%=name%></span> <br />
        <div>
            <% if (un.toString().equals("anonymousUser")) { %>
            <a href="<%=str%>" style="color: #eecd8d; font-size: 17px;">
                <i class="fa fa-sign-in"></i>
            </a>
            <% } else { %>
            <a href="<%=PathUtility.buildRedirectUrlToLogoutPage(request, response)%>"
               style="color: red; padding: 0 5px;"
               title=<s:message code="button.logout"/>>
                <i class="fa fa-power-off"></i>
            </a>
            <% }
               boolean canChangePass = false;
               if (canChangePass) {
            %>
            <a href="javascript:$('.password').val(''); $('#popup_change_pass').fadeIn();$('#currentPass').focus();"
               style="color: #eecd8d; padding: 0 5px;"
               title=<s:message code="button.changePass"/>>
                <i class="fa fa-key"></i>
            </a>
            <% } %>
        </div>
    </div>

    <% } else { %>
    <a href="${rootUrl}login" style="color: #eecd8d; font-size: 17px;">
        <i class="fa fa-sign-in"></i>
    </a>
    <% } %>
</li>