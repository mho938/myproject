package tv.samim.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import tv.samim.common.security.CurrentUser;
import tv.samim.common.security.UserDetail;
import tv.samim.template.utility.PathUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @author mojtaba khallash
 */
@Controller
public class ViewController {

    @Autowired
    private LocaleResolver localeresolver;



    @RequestMapping(value = { "/home", "/" })
    public String showHome(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {
        CurrentUser currentUser = UserDetail.getCurrentUser(request);

        return "home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(HttpServletRequest request, Model model) {
        return "about";
    }

    @RequestMapping(value = "/language", method = RequestMethod.GET)
    public String language(
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("lang") String lang,
            @RequestParam(value = "redirect", required = false) String redirect,
            Map<String, Object> model) throws IOException {
        Locale locale = new Locale(lang);
        localeresolver.setLocale(request, response, locale);
        UserDetail.getCurrentUser(request).setLocale(locale);

        if (redirect.contains("/signout") || redirect.contains("/authentication"))
            response.sendRedirect(PathUtility.buildRedirectUrlToHomePage(request, response));
        else
            response.sendRedirect(redirect);

        return null;
    }
}
