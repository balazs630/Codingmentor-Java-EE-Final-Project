package com.schonherz.project.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public final class Session {
    
    public static final String USER_SESSION_USERNAME = "user";
    
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
    }

    public static HttpSession getSession(ServletRequest servletRequest, ServletResponse servletResponse) {
        String value = "no-cache";
        ((HttpServletResponse)servletResponse).setHeader("Cache-Control", value);
        FacesContext facesContext = FacesUtil.getFacesContext(servletRequest, servletResponse);
        return (HttpSession) facesContext.getExternalContext().getSession(true);
    }


    public static String getRemoteAddress(HttpServletRequest req) {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress != null) {
            ipAddress = ipAddress.replaceFirst(",.*", ""); 
        } else {
            ipAddress = req.getRemoteAddr();
        }
        return ipAddress;
    }

    public static <T> T getAttribute(Class<T> clazz, String attribute) {
        Object obj = Session.getSession().
                getAttribute(attribute);
        T attr = null;
        if (clazz.isInstance(obj)) {
            attr = (T) obj;
        }
        return attr;
    }
    
}
