package org.cdi.jaxrs.scoped.interceptor;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@REST
@Interceptor
@RequestScoped // <-- Any scope besides @Dependent cause StackOverflow
public class ResourceInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @AroundInvoke
    public Object intercept(final InvocationContext context) throws Exception {
        return context.proceed();
    }
}
