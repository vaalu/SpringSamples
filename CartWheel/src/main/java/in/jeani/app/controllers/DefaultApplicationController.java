/**
 * 
 */
package in.jeani.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jeani.app.services.StockQuoteService;

/**
 * @author mohanavelp
 *
 */
@RestController
@RequestMapping("/app")
public class DefaultApplicationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultApplicationController.class);
	
	@Autowired
	StockQuoteService stockQuoteService;
	
	@RequestMapping("/echo")
	public String echo() {
		LOGGER.debug("Default application health is fine.");
		return "Alpha application services are up and running fine";
	}
	
	@RequestMapping("/{stock}/{timeseries}")
	public String fetch(@PathVariable("stock") String stock, @PathVariable("timeseries") int timeseries) {
		LOGGER.debug("Fetching stock details for stock {} {}", stock, timeseries);
		
		stockQuoteService.fetchHistoricStockData(stock, timeseries);
		
		return "Fetching stock details for stock";
	}
	
}
