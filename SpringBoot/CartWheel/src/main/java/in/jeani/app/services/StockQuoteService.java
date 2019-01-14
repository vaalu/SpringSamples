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

import in.jeani.app.exception.UnsupportedTimeseriesIntervalException;
import in.jeani.app.web.config.AppConfig;

/**
 * @author mohanavelp
 *
 */
@Service
public class StockQuoteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StockQuoteService.class);
	
	@Autowired
	AppConfig appConfig;
	
	public void fetchHistoricStockData(String stock, int timeseries) {
		
		Interval timeseriesInterval = getInterval(timeseries);
		String alphavantageKey = appConfig.getAlphavantageKey();
		int timeout = appConfig.getAlphavantageTimeout();
		
		LOGGER.debug("Alphavantage key: {}, time series interval: {}",alphavantageKey, timeseriesInterval);
		
		AlphaVantageConnector connector = new AlphaVantageConnector(alphavantageKey, timeout);
		TimeSeries stockTimeSeries = new TimeSeries(connector);
		
		try {
			IntraDay intradayResponse = stockTimeSeries.intraDay(stock, timeseriesInterval, OutputSize.FULL);
			Map<String, String> metadata = intradayResponse.getMetaData();
			
			LOGGER.debug("Information: {}, Stock: ", metadata.get("1. Information"), metadata.get("2. Symbol"));
			List<StockData> stockData = intradayResponse.getStockData();
			
			for (StockData data : stockData) {
				LOGGER.debug("Date: {}, Open {}, High {}, Low {}, Close {}", data.getDateTime(), data.getOpen(), data.getHigh(), data.getLow(), data.getClose());
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
}
