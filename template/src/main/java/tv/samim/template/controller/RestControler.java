package tv.samim.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mojtaba khallash
 */
@Controller
@RequestMapping(value = "/rest")
public class RestControler {

    @Autowired
    private ServletContext context;
    @Autowired
    private MessageSource source;
    @Autowired
    private SessionLocaleResolver resolver;
    private String getDictionary(HttpServletRequest request, String key) {
        return source.getMessage(key, null, resolver.resolveLocale(request));
    }

}