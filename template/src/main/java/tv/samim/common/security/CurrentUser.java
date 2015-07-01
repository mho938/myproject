package tv.samim.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Locale;

/**
 * @author mojtaba khallash and AhmadReza Nazemi
 */
public class CurrentUser extends User {
    private static final long serialVersionUID = 1L;
    private boolean authenticated;

    private String serverId;
    private String sessionId;

    private String home;
    private Locale locale;

    @SuppressWarnings("static-access")
    public CurrentUser(String username, String password, boolean isAuthenticates,
                  String serverId, String sessionId, String home, Locale locale,
                  Collection<? extends GrantedAuthority> authorities)  {
        super(username, password, authorities);
        this.authenticated = isAuthenticates;
        this.serverId = serverId;
        this.sessionId = sessionId;
        this.home = home;
        this.locale = locale;
    }

    public String getServerId() {
        return serverId;
    }

    public String getHome() {
        return home;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLang() {
        return locale.toString().trim();
    }

    public void setLocale(Locale lang) {
        this.locale = lang;
    }

    public boolean isAuthenticated() { return authenticated; }
}
