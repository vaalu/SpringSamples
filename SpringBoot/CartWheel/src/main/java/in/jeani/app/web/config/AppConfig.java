/**
 * 
 */
package in.jeani.app.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author mohanavelp
 *
 */
@Configuration
@ComponentScan({"in.jeani.app"})
public class AppConfig {

	@Value("${alphavantage.key}")
    private String alphavantageKey;
	
	@Value("${alphavantage.timeout}")
    private int alphavantageTimeout;

	/**
	 * @return the alphavantageKey
	 */
	public String getAlphavantageKey() {
		return alphavantageKey;
	}

	/**
	 * @return the alphavantageTimeout
	 */
	public int getAlphavantageTimeout() {
		return alphavantageTimeout;
	}

}