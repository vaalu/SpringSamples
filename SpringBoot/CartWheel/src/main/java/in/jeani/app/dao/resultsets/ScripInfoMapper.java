/**
 * 
 */
package in.jeani.app.dao.resultsets;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import in.jeani.app.dao.entities.ScripInfoEntity;

/**
 * @author mohanavelp
 *
 */
public class ScripInfoMapper implements RowMapper<ScripInfoEntity>{

	private static final String COL_SCRIP = "SCRIP";
	private static final String COL_SCRIP_ID = "SCRIP_ID";
	private static final String COL_SCRIP_NAME = "SCRIP_NAME";
	
	@Override
	public ScripInfoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		ScripInfoEntity entity = new ScripInfoEntity(	rs.getString(COL_SCRIP), 
														rs.getString(COL_SCRIP_NAME), 
														rs.getLong(COL_SCRIP_ID));
		return entity;
	}

}
