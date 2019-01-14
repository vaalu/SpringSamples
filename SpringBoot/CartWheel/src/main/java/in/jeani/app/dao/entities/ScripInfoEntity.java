/**
 * 
 */
package in.jeani.app.dao.entities;

/**
 * @author mohanavelp
 *
 */
public class ScripInfoEntity {

	/**
	 * 
	 */
	public ScripInfoEntity() {
	}
	
	/**
	 * Constructor with scrip information prefilled
	 * @param scrip
	 * @param scripName
	 * @param scripId
	 */
	public ScripInfoEntity(String scrip, String scripName, long scripId) {
		this.scrip = scrip;
		this.scripName = scripName;
		this.scripId = scripId;
	}
	
	private String scrip;
	private String scripName;
	private long scripId;
	
	/**
	 * @return the scrip
	 */
	public String getScrip() {
		return scrip;
	}
	/**
	 * @param scrip the scrip to set
	 */
	public void setScrip(String scrip) {
		this.scrip = scrip;
	}
	/**
	 * @return the scripName
	 */
	public String getScripName() {
		return scripName;
	}
	/**
	 * @param scripName the scripName to set
	 */
	public void setScripName(String scripName) {
		this.scripName = scripName;
	}
	/**
	 * @return the scripId
	 */
	public long getScripId() {
		return scripId;
	}
	/**
	 * @param scripId the scripId to set
	 */
	public void setScripId(long scripId) {
		this.scripId = scripId;
	}
	
}
