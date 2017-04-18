package com.schonherz.project.util;

import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by darvasr on 2016.09.18..
 */
public class FacesUtil {

    public static FacesContext getFacesContext(final ServletRequest request, final ServletResponse response)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null)
        {
            return facesContext;
        }

        FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder
                .getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
        LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder
                .getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

        facesContext = contextFactory.getFacesContext(
                request.getServletContext(), request, response, lifecycle);

        UIViewRoot view = facesContext.getApplication().getViewHandler().createView(
                facesContext, "");
        facesContext.setViewRoot(view);
        return facesContext;
    }

}
