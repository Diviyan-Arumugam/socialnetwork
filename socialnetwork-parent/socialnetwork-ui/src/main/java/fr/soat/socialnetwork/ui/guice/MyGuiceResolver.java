package fr.soat.socialnetwork.ui.guice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.PropertyNotFoundException;
import javax.faces.FacesException;
import javax.faces.application.ProjectStage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.myfaces.config.element.ManagedBean;
import org.apache.myfaces.context.servlet.StartupServletExternalContextImpl;
import org.apache.myfaces.el.unified.resolver.ManagedBeanResolver;

import com.google.inject.Injector;

public class MyGuiceResolver extends ManagedBeanResolver
{
	private static final Logger log = Logger.getLogger(MyGuiceResolver.class.getName());
	
    public static final String KEY = "oam." + Injector.class.getName();

    private static final String BEANS_UNDER_CONSTRUCTION =
            "org.apache.myfaces.el.unified.resolver.managedbean.beansUnderConstruction";

    private static final String CUSTOM_SCOPE_CYCLIC_REFERENCE_DETECTION =
            "org.apache.myfaces.el.unified.resolver.managedbean.customScopeCyclicReferenceDetection";
    
    interface Scope
    {
        public void put(FacesContext facesContext, ExternalContext extContext, String name, Object obj);
    }
    
    protected static final Map<String, Scope> STANDARD_SCOPES = new HashMap<String, Scope>(16);

    static
    {
        STANDARD_SCOPES.put("request", new Scope()
        {
            public void put(FacesContext facesContext, ExternalContext extContext, String name, Object obj)
            {
                extContext.getRequestMap().put(name, obj);
            }
        });

        STANDARD_SCOPES.put("session", new Scope()
        {
            public void put(FacesContext facesContext, ExternalContext extContext, String name, Object obj)
            {
                extContext.getSessionMap().put(name, obj);
            }
        });

        STANDARD_SCOPES.put("application", new Scope()
        {
            public void put(FacesContext facesContext, ExternalContext extContext, String name, Object obj)
            {
                extContext.getApplicationMap().put(name, obj);
            }
        });

        STANDARD_SCOPES.put("none", new Scope()
        {
            public void put(FacesContext facesContext, ExternalContext extContext, String name, Object obj)
            {
                // do nothing
            }
        });
        
        // jsf 2.0 view scope
        STANDARD_SCOPES.put("view", new Scope()
        {
            public void put(FacesContext facesContext, ExternalContext extContext, String name, Object obj)
            {
                facesContext.getViewRoot().getViewMap().put(name, obj);
            }
        });
    }
    
    
    protected final Map<String, Scope> _scopes = new HashMap<String, Scope>(16);
    {
        _scopes.putAll(STANDARD_SCOPES);
    }

    // get the FacesContext from the ELContext
    private static FacesContext facesContext(final ELContext context)
    {
    	return (FacesContext)context.getContext(FacesContext.class);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Object getValue(final ELContext context, final Object base, final Object property)
        throws NullPointerException, PropertyNotFoundException, ELException
    {
	    // we only resolve ManagedBean instances, not properties of those  
	    if (base != null)
	    {
	        return null;
	    }
	
	    if (property == null)
	    {
	        throw new PropertyNotFoundException();
	    }
	
	    final FacesContext facesContext = facesContext(context);
	    if (facesContext == null)
	    {
	        return null;
	    }
	    final ExternalContext extContext = facesContext.getExternalContext();
	    if (extContext == null)
	    {
	        return null;
	    }
	
	    final boolean startup = (extContext instanceof StartupServletExternalContextImpl);
	    
	    // request scope (not available at startup)
	    if (!startup)
	    {
	        if (extContext.getRequestMap().containsKey(property))
	        {
	            return null;
	        }
	    }
	
	    // view scope
	    UIViewRoot root = facesContext.getViewRoot();
	    if (root != null)
	    {
	        Map<String, Object> viewMap = root.getViewMap(false);
	        if (viewMap != null && viewMap.containsKey(property))
	        {
	            return null;
	        }
	    }
	
	    // session scope (not available at startup)
	    if (!startup)
	    {
	        if (extContext.getSessionMap().containsKey(property))
	        {
	            return null;
	        }
	    }
	
	    // application scope
	    if (extContext.getApplicationMap().containsKey(property))
	    {
	        return null;
	    }
	
	    // not found in standard scopes - get ManagedBean metadata object
	    // In order to get the metadata object, we need property to be the managed bean name (--> String)
	    if (!(property instanceof String))
	    {
	        return null;
	    }
	    final String strProperty = (String) property;
	
	    final ManagedBean managedBean = runtimeConfig(context).getManagedBean(strProperty);
	    Object beanInstance = null;
	    if (managedBean != null)
	    {
	        context.setPropertyResolved(true);
	        
	        // managed-bean-scope could be a ValueExpression pointing to a Map (since 2.0) --> custom scope
	        if (managedBean.isManagedBeanScopeValueExpression())
	        {
	            // check for cyclic references in custom scopes, if we are not in Production stage
	            boolean checkCyclicReferences = !facesContext.isProjectStage(ProjectStage.Production);
	            List<String> cyclicReferences = null;
	            
	            if (checkCyclicReferences)
	            {
	                final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
	                final String managedBeanName = managedBean.getManagedBeanName();
	                
	                cyclicReferences = (List<String>) requestMap.get(CUSTOM_SCOPE_CYCLIC_REFERENCE_DETECTION);
	                if (cyclicReferences == null)
	                {
	                    cyclicReferences = new ArrayList<String>();
	                    requestMap.put(CUSTOM_SCOPE_CYCLIC_REFERENCE_DETECTION, cyclicReferences);
	                }
	                else if (cyclicReferences.contains(managedBeanName))
	                {
	                    throw new ELException("Detected cyclic reference to managedBean " + managedBeanName);
	                }
	
	                cyclicReferences.add(managedBeanName);
	            }
	            try
	            {
	                Object customScope = managedBean.getManagedBeanScopeValueExpression(facesContext)
	                                            .getValue(facesContext.getELContext());
	                if (customScope instanceof Map)
	                {
	                    beanInstance = ((Map) customScope).get(managedBean.getManagedBeanName());
	                }
	                else if (customScope != null)
	                {
	                    throw new FacesException("The expression '" + managedBean.getManagedBeanScope() + 
	                            "' does not evaluate to java.util.Map. It evaluates to '" + customScope + 
	                            "' of type " + customScope.getClass().getName());
	                }
	                else
	                {
	                    log.warning("Custom scope '" + managedBean.getManagedBeanScope() +
	                            "' evaluated to null. Unable to determine if managed bean '" +
	                            managedBean.getManagedBeanName() + "' exists.");
	                }
	            }
	            finally
	            {
	                if (checkCyclicReferences)
	                {
	                    cyclicReferences.remove(managedBean.getManagedBeanName());
	                }
	            }
	        }
	
	        // not found in any scope - create instance!
	        if (beanInstance == null)
	        {
	            beanInstance = createManagedBean(managedBean, facesContext);
	        }
	    }
	
	    return beanInstance;
	}

	// Create a managed bean. If the scope of the bean is "none" then
	// return it right away. Otherwise store the bean in the appropriate
	// scope and return null.
	//
	// adapted from Manfred's JSF 1.1 VariableResolverImpl
	@SuppressWarnings("unchecked")
	private Object createManagedBean(final ManagedBean managedBean, final FacesContext facesContext) throws ELException
	{
	
	    final ExternalContext extContext = facesContext.getExternalContext();
	    final Map<Object, Object> facesContextMap = facesContext.getAttributes();
	    final String managedBeanName = managedBean.getManagedBeanName();
	
	    // check for cyclic references
	    List<String> beansUnderConstruction = (List<String>) facesContextMap.get(BEANS_UNDER_CONSTRUCTION);
	    if (beansUnderConstruction == null)
	    {
	        beansUnderConstruction = new ArrayList<String>();
	        facesContextMap.put(BEANS_UNDER_CONSTRUCTION, beansUnderConstruction);
	    }
	    else if (beansUnderConstruction.contains(managedBeanName))
	    {
	        throw new ELException("Detected cyclic reference to managedBean " + managedBeanName);
	    }
	
	    beansUnderConstruction.add(managedBeanName);
	
	    Object obj = null;
	    try
	    {
	    	obj = getGuiceInstanceValue(extContext, managedBean.getManagedBeanClass());
	    }
	    finally
	    {
	        beansUnderConstruction.remove(managedBeanName);
	    }
	
	    putInScope(managedBean, facesContext, extContext, obj);
	
	    return obj;
	}
	
	@SuppressWarnings("unchecked")
	private void putInScope(final ManagedBean managedBean, final FacesContext facesContext,
	        final ExternalContext extContext, final Object obj)
	{
	
	    final String managedBeanName = managedBean.getManagedBeanName();
	
	    if (obj == null)
	    {
	        if (log.isLoggable(Level.FINE))
	        {
	            log.fine("Variable '" + managedBeanName + "' could not be resolved.");
	        }
	    }
	    else
	    {
	        final String scopeKey = managedBean.getManagedBeanScope();
	
	        // find the scope handler object
	        final Scope scope = _scopes.get(scopeKey);
	        if (scope != null)
	        {
	            scope.put(facesContext, extContext, managedBeanName, obj);
	        }
	        else if (managedBean.isManagedBeanScopeValueExpression())
	        {
	            // managed-bean-scope could be a ValueExpression pointing to a Map (since 2.0)
	            // Optimisation: We do NOT check for cyclic references here, because when we reach this code,
	            // we have already checked for cyclic references in the custom scope
	            Object customScope = managedBean.getManagedBeanScopeValueExpression(facesContext)
	                    .getValue(facesContext.getELContext());
	            
	            if (customScope instanceof Map)
	            {
	                ((Map) customScope).put(managedBeanName, obj);
	            }
	            else if (customScope != null)
	            {
	                throw new FacesException("The expression '" + scopeKey + "' does not evaluate to " +
	                        "java.util.Map. It evaluates to '" + customScope + "' of type " + 
	                        customScope.getClass().getName());
	            }
	            else
	            {
	                log.warning("Custom scope '" + scopeKey + "' evaluated to null. " +
	                        "Cannot store managed bean '" + managedBeanName + "' in custom scope.");
	            }
	        }
	        else
	        {
	            log.severe("Managed bean '" + managedBeanName + "' has illegal scope: " + scopeKey);
	        }
	    }
	}

    private Object getGuiceInstanceValue(ExternalContext ectx, Class<?> managedBeanClass)
    {
        Injector injector = (Injector)ectx.getApplicationMap().get(KEY);

        if (injector == null)
        {
            throw new FacesException("Could not find an instance of " + Injector.class.getName()
                    + " in application scope using key '" + KEY + "'");
        }
        Object value = injector.getInstance(managedBeanClass);
        log.info("Created guice object " + value);

        return value;
    }
}
