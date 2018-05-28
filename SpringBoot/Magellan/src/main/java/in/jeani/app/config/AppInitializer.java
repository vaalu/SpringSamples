/**
 * 
 */
package in.jeani.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import in.jeani.app.MagellanStartup;

/**
 * @author mohanavelp
 *
 */
@EnableAutoConfiguration
public class AppInitializer extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		builder.sources(MagellanStartup.class);
		return builder;
	}
}
