package org.cdi.jaxrs.scoped.interceptor.test;

import java.net.HttpURLConnection;
import java.net.URL;

import org.cdi.jaxrs.scoped.interceptor.Resource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ScopedInterceptorStackOverflowTestCase {

    @Deployment(testable = false)
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
    
    @ArquillianResource
    private URL baseURL;
    
    @Test
    public void shouldReturnOk() throws Exception {
        HttpURLConnection con = (HttpURLConnection)new URL(baseURL, "api/test").openConnection();
        Assert.assertEquals(200, con.getResponseCode());
    }
}
