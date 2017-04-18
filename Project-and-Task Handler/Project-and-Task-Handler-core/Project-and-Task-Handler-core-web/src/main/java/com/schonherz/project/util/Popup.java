package com.schonherz.project.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public final class Popup {

    public static final String GROWL_ID = "growl";

    public static final String PROFLE_SETTING = "messages";

    public static void pushPopup(String header, String body) {
        pushPopup(GROWL_ID, header, body);
    }
    
    public static void pushInfo(String header, String body) {
        pushPopup(header, body);
    }
    
    public static void pushError(String header, String body) {
        pushPopup(FacesMessage.SEVERITY_ERROR, header, body);
    }

    public static void pushPopup(String messageId, String header, String body) {
        pushPopup(FacesMessage.SEVERITY_INFO, messageId, header, body);
    }

    public static void pushPopup(Severity severity, String header, String body) {
        pushPopup(severity, GROWL_ID, header, body);
    }

    public static void pushPopup(Severity severity, String messageId, String header, String body) {
        RequestContext.getCurrentInstance().update(messageId);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(severity, header, body));
    }
}
