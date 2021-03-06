package tv.samim.common.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import tv.samim.template.utility.PathUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author mojtaba khallash and AhmadReza Nazemi
 */
public class WelcomeAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        if (true) {
            redirectStrategy.sendRedirect(request, response,
                    PathUtility.buildRedirectUrlToLoginPage(request, response));
            return;
        }
        wellcomeAuth(request, response, authException);
    }

    public static void wellcomeAuth(HttpServletRequest request,
                                    HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        String ip = request.getServerName();
        switch (ip) {
            case "0:0:0:0:0:0:0:1":

                break;
            case "localhost":
                ip = "127.0.0.1";
                break;
        }
        String redirectUrl = PathUtility.buildRedirectUrlToCurrentPath(request, response);
        boolean is_auth = false;
        // don't create if it doesn't exist
        HttpSession session = request.getSession(false);
        try {
            if (session != null && !session.isNew()) {
                is_auth = Boolean.parseBoolean(session.getAttribute("IS_AUTHENTICATE").toString());
            }
        } catch (Exception ex) {
            ///Logger.e(ex);
        }

        // obtain the requested URI
        String requestUri = request.getRequestURI();
        String sitePath = requestUri.substring(0, requestUri.indexOf('/', 2));
        if (is_auth == false) {
            redirectStrategy.sendRedirect(request, response,
                    /*Config.i().getWelcomeRoot() + "login.jsp?redirect=" + redirectUrl*/"");
        } else {
            if (requestUri.equals(sitePath + "/login.jsp") == true && (is_auth == true)) {
                response.sendRedirect("index.jsp");
            }
        }
    }
}