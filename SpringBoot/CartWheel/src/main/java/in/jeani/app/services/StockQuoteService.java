/**
 * 
 */
package in.jeani.app.services;

import java.util.List;
import java.util.Map;

import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jeani.app.dao.StockQuoteDAO;
import in.jeani.app.dao.entities.ScripInfoEntity;
import in.jeani.app.exception.UnsupportedTimeseriesIntervalException;
import in.jeani.app.web.config.AppConfig;

/**
 * @author mohanavelp
 * Simple service to fetch quote related details such as historic data, etc.,
 */
@Service
public class StockQuoteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StockQuoteService.class);
	
	@Autowired
	AppConfig appConfig;
	
	@Autowired
	StockQuoteDAO stockQuoteDAO;
	
	/**
	 * Fetch historic data from alphavantage api and save them in DB
	 * @param stock
	 * @param timeseries
	 * @param limit
	 */
	public List<StockData> fetchHistoricStockData(String stock, int timeseries, String limit) {
		
		Interval timeseriesInterval = getInterval(timeseries);
		String alphavantageKey = appConfig.getAlphavantageKey();
		int timeout = appConfig.getAlphavantageTimeout();
		OutputSize limitSize = getOutputLimit(limit);
		LOGGER.debug("Alphavantage key: {}, time series interval: {} with limit size: {}",alphavantageKey, timeseriesInterval, limitSize);
		
		AlphaVantageConnector connector = new AlphaVantageConnector(alphavantageKey, timeout);
		TimeSeries stockTimeSeries = new TimeSeries(connector);
		List<StockData> stockData = null;
		
		try {
			IntraDay intradayResponse = stockTimeSeries.intraDay(stock, timeseriesInterval, limitSize);
			Map<String, String> metadata = intradayResponse.getMetaData();
			
			LOGGER.debug("Information: {}, Stock: ", metadata.get("1. Information"), metadata.get("2. Symbol"));
			stockData = intradayResponse.getStockData();
			
			for (StockData data : stockData) {
				LOGGER.debug("Date: {}, Open {}, High {}, Low {}, Close {}", data.getDateTime(), data.getOpen(), data.getHigh(), data.getLow(), data.getClose());
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return stockData;
	}
	
	/**
	 * Fetch all the available scrips information
	 * @return {@link List<{@link <ScripInfoEntity>}>}
	 */
	public List<ScripInfoEntity> fetchAvailableScrips() {
		return stockQuoteDAO.getAvailableScrips();
	}
	
	/**
	 * Fetch all the available scrips information
	 * @return {@link List<{@link <ScripInfoEntity>}>}
	 */
	public List<ScripInfoEntity> fetchAvailableScripsStartingWith(String wildCard) {
		return stockQuoteDAO.getAvailableScripsStartingWith(wildCard);
	}
	
	/**
	 * Fetch all the available scrips information
	 * @return {@link List<{@link <ScripInfoEntity>}>}
	 */
	public List<ScripInfoEntity> fetchAvailableScripsEndingWith(String wildCard) {
		return stockQuoteDAO.getAvailableScripsEndingWith(wildCard);
	}
	
	/**
	 * Fetch all the available scrips information
	 * @return {@link List<{@link <ScripInfoEntity>}>}
	 */
	public List<ScripInfoEntity> fetchAvailableScripsContaining(String wildCard) {
		return stockQuoteDAO.getAvailableScripsContaining(wildCard);
	}
	
	/**
	 * Fetch interval from enum
	 * @param interval
	 * @return {@link Interval}
	 */
	private Interval getInterval(int interval) {
		
		Interval timeSeriesInterval = null;
		String val = "" + interval + "min";
		try {
			Interval[] seriesValues = Interval.values();
			for (Interval series : seriesValues) {
				if (series.getValue().equalsIgnoreCase(val)) {
					timeSeriesInterval = series;
					break;
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new UnsupportedTimeseriesIntervalException("Provided timeseries for the stock quote is invalid " + interval);
		}
		
		if(null == timeSeriesInterval) {
			throw new UnsupportedTimeseriesIntervalException("Provided timeseries for the stock quote is invalid " + interval);
		}		
		return timeSeriesInterval;
	}
	
	/**
	 * Fetch output size from enum
	 * @param limit
	 * @return {@link OutputSize}
	 */
	private OutputSize getOutputLimit(String limit) {
		
		OutputSize limitSize = null;
		if(null == limit) {
			return OutputSize.COMPACT;
		}
		try {
			OutputSize[] seriesValues = OutputSize.values();
			for (OutputSize series : seriesValues) {
				if (series.getValue().equalsIgnoreCase(limit)) {
					limitSize = series;
					break;
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new UnsupportedTimeseriesIntervalException("Provided output limit for the stock quote is invalid " + limitSize);
		}
		
		if(null == limitSize) {
			throw new UnsupportedTimeseriesIntervalException("Provided output limit for the stock quote is invalid " + limitSize);
		}		
		return limitSize;
	}
	
}
