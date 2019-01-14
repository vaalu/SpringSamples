/**
 * 
 */
package in.jeani.app.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.patriques.output.timeseries.data.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.jeani.app.dao.entities.ScripInfoEntity;
import in.jeani.app.dao.resultsets.ScripInfoMapper;

/**
 * @author mohanavelp
 *
 */
@Repository
public class StockQuoteDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Fetch all the available scrips information
	 * @return {@link List<{@link <ScripInfoEntity>}>}
	 */
	public List<ScripInfoEntity> getAvailableScrips() {
		
		List<ScripInfoEntity> scrips = null;
		
		String selectQuery = "SELECT \"SCRIP\", \"SCRIP_ID\", \"SCRIP_NAME\" FROM \"ETLREWRITE\".\"SCRIPS\"";
		scrips = jdbcTemplate.query(selectQuery, new ScripInfoMapper());
		
		return scrips;
	}
	
	/**
	 * Fetch all the available scrips information
	 * @return {@link List<{@link <ScripInfoEntity>}>}
	 */
	public List<ScripInfoEntity> getAvailableScripsStartingWith(String wildCard) {
		
		List<ScripInfoEntity> scrips = null;
		if (null != wildCard) {
			wildCard = wildCard.toUpperCase();
		}
		String selectQuery = "SELECT \"SCRIP\", \"SCRIP_ID\", \"SCRIP_NAME\" FROM \"ETLREWRITE\".\"SCRIPS\" WHERE \"SCRIP\" LIKE '"+wildCard+"%'";
		scrips = jdbcTemplate.query(selectQuery, new ScripInfoMapper());
		
		return scrips;
	}
	
	/**
	 * Fetch all the available scrips information
	 * @return {@link List<{@link <ScripInfoEntity>}>}
	 */
	public List<ScripInfoEntity> getAvailableScripsEndingWith(String wildCard) {
		
		List<ScripInfoEntity> scrips = null;
		if (null != wildCard) {
			wildCard = wildCard.toUpperCase();
		}
		String selectQuery = "SELECT \"SCRIP\", \"SCRIP_ID\", \"SCRIP_NAME\" FROM \"ETLREWRITE\".\"SCRIPS\" WHERE \"SCRIP\" LIKE '%"+wildCard+"'";
		scrips = jdbcTemplate.query(selectQuery, new ScripInfoMapper());
		
		return scrips;
	}

	/**
	 * Fetch all the available scrips information
	 * @return {@link List<{@link <ScripInfoEntity>}>}
	 */
	public List<ScripInfoEntity> getAvailableScripsContaining(String wildCard) {
		
		List<ScripInfoEntity> scrips = null;
		if (null != wildCard) {
			wildCard = wildCard.toUpperCase();
		}
		String selectQuery = "SELECT \"SCRIP\", \"SCRIP_ID\", \"SCRIP_NAME\" FROM \"ETLREWRITE\".\"SCRIPS\" WHERE \"SCRIP\" LIKE '%"+wildCard+"%'";
		scrips = jdbcTemplate.query(selectQuery, new ScripInfoMapper());
		
		return scrips;
	}
	
	public void addHistoricData(final String scrip, final List<StockData> stockData) {
		String insertQuery = "INSERT INTO \"ETLREWRITE\".\"HISTORICAL_INTRADAY_DATA\"\r\n" + 
				"(\"SCRIP\", \"DATE_TIME\", \"OPEN\", \"HIGH\", \"LOW\", \"CLOSE\", \"ADJUSTED_CLOSE\", \"VOLUME\", \"DIVIDEND_AMOUNT\", \"SPREAD\")\r\n" + 
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if(stockData != null) {
			final int batchSize = stockData.size();
			jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					StockData stock = stockData.get(i);
					Timestamp tickTime = Timestamp.valueOf(stock.getDateTime());
					
					Double open = stock.getOpen();
					Double high = stock.getHigh();
					Double low = stock.getLow();
					Double close = stock.getClose();
					Double adjClose = stock.getAdjustedClose();
					long volume = stock.getVolume();
					Double dividendAmt = stock.getDividendAmount();
					Double spread = stock.getSplitCoefficient();
					
					ps.setString(1, scrip);
					ps.setTimestamp(2, tickTime, Calendar.getInstance());
					ps.setDouble(3, open);
					ps.setDouble(4,  high);
					ps.setDouble(5, low);
					ps.setDouble(6, close);
					ps.setDouble(7, adjClose);
					ps.setLong(8, volume);
					ps.setDouble(9, dividendAmt);
					ps.setDouble(10,  spread);
					
				}
				
				@Override
				public int getBatchSize() {
					return batchSize;
				}
			});
		}
	}
}
