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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.jeani.app.dao.entities.ScripInfoEntity;
import org.springframework.web.bind.annotation.RestController;

import in.jeani.app.services.StockQuoteService;

/**
 * @author mohanavelp
 *
 */
@RestController
@RequestMapping("/api")
public class DefaultApplicationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultApplicationController.class);
	
	@Autowired
	StockQuoteService stockQuoteService;
	
	/**
	 * @return simple echo for a rest controller
	 */
	@RequestMapping(value="/echo", method=RequestMethod.GET)
	public String echo() {
		LOGGER.debug("Default application health is fine.");
		return "Alpha application services are up and running fine";
	}
	
	/**
	 * Fetch intraday data limited to first 100 available data
	 * @param stock
	 * @param timeseries
	 * @return {@link List<{@Link StockData}>}
	 */
	@RequestMapping(value= {"/{stock}/{timeseries}"}, method=RequestMethod.GET)
	public List<StockData> fetchDefault(@PathVariable("stock") String stock, @PathVariable("timeseries") int timeseries) {
		LOGGER.debug("Fetching stock details for stock {} {} with limit: ", stock, timeseries, null);
		
		List<StockData> stockData = stockQuoteService.fetchHistoricStockData(stock, timeseries, null);
		
		return stockData;
	}
	
	/**
	 * Fetch all available intraday data with or without limit compact | all
	 * @param stock
	 * @param timeseries
	 * @param limit
	 * @return {@link List<{@Link StockData}>}
	 */
	@RequestMapping(value="/{stock}/{timeseries}/{limit}", method=RequestMethod.GET)
	public List<StockData> fetch(@PathVariable("stock") String stock, @PathVariable("timeseries") int timeseries, @PathVariable("limit") String limit) {
		LOGGER.debug("Fetching stock details for stock {} {} with limit: ", stock, timeseries, limit);
		
		List<StockData> stockData = stockQuoteService.fetchHistoricStockData(stock, timeseries, limit);
		
		return stockData;
	}
	
	/**
	 * Fetch all available scrips
	 * @return
	 */
	@RequestMapping(value="/scrips", method=RequestMethod.GET)
	public List<ScripInfoEntity> fetchScrips() {
		LOGGER.debug("Fetching available scrips (all available)");
		return stockQuoteService.fetchAvailableScrips();
	}
	
	/**
	 * Fetch all available scrips using wildcard that starts with
	 * @return
	 */
	@RequestMapping(value="/scrips/startingwith/{wildcard}", method=RequestMethod.GET)
	public List<ScripInfoEntity> fetchScrips(@PathVariable("wildcard") String wildCard) {
		LOGGER.debug("Fetching available scrips (all available)");
		return stockQuoteService.fetchAvailableScripsStartingWith(wildCard);
	}
	

	/**
	 * Fetch all available scrips using wildcard that ends with
	 * @return
	 */
	@RequestMapping(value="/scrips/endingwith/{wildcard}", method=RequestMethod.GET)
	public List<ScripInfoEntity> fetchScripsEnding(@PathVariable("wildcard") String wildCard) {
		LOGGER.debug("Fetching available scrips (all available)");
		return stockQuoteService.fetchAvailableScripsEndingWith(wildCard);
	}

	/**
	 * Fetch all available scrips using wildcard that contains
	 * @return
	 */
	@RequestMapping(value="/scrips/containing/{wildcard}", method=RequestMethod.GET)
	public List<ScripInfoEntity> fetchScripsContaining(@PathVariable("wildcard") String wildCard) {
		LOGGER.debug("Fetching available scrips (all available)");
		return stockQuoteService.fetchAvailableScripsContaining(wildCard);
	}
}
