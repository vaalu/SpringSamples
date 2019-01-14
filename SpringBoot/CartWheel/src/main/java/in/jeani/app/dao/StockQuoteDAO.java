/**
 * 
 */
package in.jeani.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
