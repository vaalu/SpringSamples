/**
 * 
 */
package in.jeani.app.exception;

import org.patriques.output.AlphaVantageException;

/**
 * @author mohanavelp
 *
 */
public class UnsupportedTimeseriesIntervalException extends AlphaVantageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsupportedTimeseriesIntervalException(String message) {
		super(message);
	}
	
}
