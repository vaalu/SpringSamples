/**
 * 
 */
package in.jeani.app.rest.simple;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mohanavelp
 *
 */
@RestController
@RequestMapping("/api")
public class SimpleRestController {

	@RequestMapping("/echo")
	public String echo() {
		return "Magellan services are up and running fine";
	}

}
