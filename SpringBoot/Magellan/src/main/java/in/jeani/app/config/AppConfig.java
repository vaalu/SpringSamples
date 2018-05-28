/**
 * 
 */
package in.jeani.app.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mohanavelp
 *
 */
@Configuration
public class AppConfig {

	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean h2Servlet = new ServletRegistrationBean(new WebServlet());
		h2Servlet.addUrlMappings("/console/*");
		return h2Servlet;
	}
	
}
