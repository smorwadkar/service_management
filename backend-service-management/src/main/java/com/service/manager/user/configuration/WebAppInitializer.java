package com.service.manager.user.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



/**
 * @author smorwadkar
 *		Class to specify web configuration.	
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
//    	this.createFilters(servletContext);
        super.onStartup(servletContext);
    }
    
    /**
     * This method creates filters and adds them to {@code ServletContext}
     * 
     * @param servletContext
     *            {@code ServletContext} instance
     */
    private void createFilters(final ServletContext servletContext) {
	// add backend proxy filter
	
	/*servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
		.addMappingForUrlPatterns(null, false, "/*");*/

    }

}