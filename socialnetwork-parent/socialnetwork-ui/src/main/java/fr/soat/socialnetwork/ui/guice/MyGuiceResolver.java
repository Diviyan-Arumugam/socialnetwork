package fr.soat.socialnetwork.ui.guice;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.PropertyNotFoundException;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.myfaces.config.element.ManagedBean;
import org.apache.myfaces.el.unified.resolver.ManagedBeanResolver;

import com.google.inject.Injector;

public class MyGuiceResolver extends ManagedBeanResolver
{

    public static final String KEY = "oam." + Injector.class.getName();

    @Override
    public Object getValue(ELContext ctx, Object base, Object property) throws NullPointerException,
        PropertyNotFoundException, ELException
    {

        if (base != null || !(property instanceof String))
        {
            return null;
        }

        FacesContext fctx = (FacesContext)ctx.getContext(FacesContext.class);

        if (fctx == null)
        {
            return null;
        }

        ExternalContext ectx = fctx.getExternalContext();

        if (ectx == null || ectx.getRequestMap().containsKey(property) || ectx.getSessionMap().containsKey(property)
                || ectx.getApplicationMap().containsKey(property))
        {
            return null;
        }

        ManagedBean managedBean = runtimeConfig(ctx).getManagedBean((String)property);

        return managedBean == null ? null : getValue(ctx, ectx, managedBean.getManagedBeanClass());
    }

    private Object getValue(ELContext ctx, ExternalContext ectx, Class<?> managedBeanClass)
    {

        Injector injector = (Injector)ectx.getApplicationMap().get(KEY);

        if (injector == null)
        {
            throw new FacesException("Could not find an instance of " + Injector.class.getName()
                    + " in application scope using key '" + KEY + "'");
        }

        Object value = injector.getInstance(managedBeanClass);
        ctx.setPropertyResolved(true);
        return value;
    }

}
