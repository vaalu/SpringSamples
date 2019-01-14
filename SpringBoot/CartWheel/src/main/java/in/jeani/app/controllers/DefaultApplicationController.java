/**
 * 
 */
package in.jeani.app.controllers;

import java.util.List;

import org.patriques.output.timeseries.data.StockData;
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
	
	@RequestMapping(value= {"/{stock}/{timeseries}"})
	public List<StockData> fetchDefault(@PathVariable("stock") String stock, @PathVariable("timeseries") int timeseries) {
		LOGGER.debug("Fetching stock details for stock {} {} with limit: ", stock, timeseries, null);
		
		List<StockData> stockData = stockQuoteService.fetchHistoricStockData(stock, timeseries, null);
		
		return stockData;
	}
	
	@RequestMapping("/{stock}/{timeseries}/{limit}")
	public List<StockData> fetch(@PathVariable("stock") String stock, @PathVariable("timeseries") int timeseries, @PathVariable("limit") String limit) {
		LOGGER.debug("Fetching stock details for stock {} {} with limit: ", stock, timeseries, limit);
		
		List<StockData> stockData = stockQuoteService.fetchHistoricStockData(stock, timeseries, limit);
		
		return stockData;
	}
	
}
