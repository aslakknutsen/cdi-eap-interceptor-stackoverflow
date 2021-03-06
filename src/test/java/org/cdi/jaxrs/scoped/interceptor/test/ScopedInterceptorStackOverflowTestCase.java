package org.cdi.jaxrs.scoped.interceptor.test;

import javax.inject.Inject;

import org.cdi.jaxrs.scoped.interceptor.Resource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ScopedInterceptorStackOverflowTestCase {

    @Deployment()
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class)
            .addPackage(Resource.class.getPackage())
            .addAsWebInfResource(
                new StringAsset("<?xml version=\"1.0\"?>" +
                    "<beans>" +
                      "<interceptors>" +
                        "<class>org.cdi.jaxrs.scoped.interceptor.ResourceInterceptor</class>" +
                      "</interceptors>" +
                    "</beans>"),
                    "beans.xml");
    }
    
    @Inject
    private Resource resource;
    
    @Test
    public void shouldReturnOk() throws Exception {
        resource.test();
    }
}
