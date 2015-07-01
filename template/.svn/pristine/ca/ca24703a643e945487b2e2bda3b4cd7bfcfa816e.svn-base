<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<c:set var="req_username">
    <s:message code="error.req.username" />
</c:set>
<c:set var="req_password">
    <s:message code="error.req.password" />
</c:set>


<form id="loginForm" role="form" name="form"
      action="j_spring_security_check" method="post">

    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <div class="alert alert-danger">
            <div class="validation-summary-errors">
                <ul style="margin:0px;">
                    <li><s:message code="error.login.fail" /></li>
                </ul>
            </div>
        </div>
        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
    </c:if>

    <div class="form-group">
        <label for="username"> <s:message code="login.username" />
        </label>
        <div class="input-group">
            <span class="input-group-addon"><i class="fa fa-user"></i></span>
            <input id="username"
                   type="text"
                   name="j_username"
                   class="form-control"
                   data-val-required="${req_username}"
                   style="direction: ltr;"
                   maxlength="45" />
        </div>
        <span data-for='j_username' class='k-invalid-msg'></span>
    </div>

    <div class="form-group">
        <label for="password"> <s:message code="login.password" />
        </label>
        <div class="input-group">
            <span class="input-group-addon"><i class="fa fa-key"></i></span>
            <input id="password"
                   type="password"
                   name="j_password"
                   class="form-control"
                   data-val-required="${req_password}"
                   style="direction: ltr;"
                   value=""
                   maxlength="45" />
        </div>
        <span data-for='j_password' class='k-invalid-msg'></span>
    </div>

    <div>
        <button id="submit" class="k-button pull-btn" type="submit"
                onclick="return validator.validate();"
                style="padding-top: 2px; padding-bottom: 2px;">
            <s:message code="login.button" />
        </button>
    </div>
</form>