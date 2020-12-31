package com.adriandevv.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Esta clase define la inicialización del contenedor de Spring. Incializa: El contenedor de Spring y el Servlet que actúa como Front-Controller.
 * Delante de este Front-Controller se establece una cadena de filtros de seguridad que corresponden a la autenticación (Login)
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
public class SpringWebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ConfigSpringAPP.class);
        applicationContext.setServletContext(servletContext);

        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");

        // Spring Security Filter
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
    }
}
