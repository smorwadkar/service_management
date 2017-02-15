package com.service.manager.user.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 * 
 * Class to specify web configuration.
 * 
 * @author sdumbre
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MvcConfig.class,AppConfig.class,MysqlDataBaseConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
    	return new Class[] { MvcConfig.class,AppConfig.class,MysqlDataBaseConfig.class};
    }
    
    @Override
    public void onStartup(ServletContext servletContext) 
            throws ServletException {

        super.onStartup(servletContext);
    }
    

}