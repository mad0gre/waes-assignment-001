package victor.waes.business;

import victor.waes.dto.DiffStatusDTO;

/**
 * Interface for the diff business logic.
 */
public interface DiffBusiness {

	/**
	 * Store right data under a specified ID.
	 * 
	 * @param id
	 *            ID under which right data will be stored.
	 * @param data
	 *            Data to be stored. It must be base64 encoded.
	 * 
	 * @return <code>true</code> if data was stored. <code>false</code> otherwise.
	 */
	boolean saveRight(String id, String data);
	
	/**
	 * Store left data under a specified ID.
	 * 
	 * @param id
	 *            ID under which left data will be stored.
	 * @param data
	 *            Data to be stored. It must be base64 encoded.
	 * 
	 * @return <code>true</code> if data was stored. <code>false</code> otherwise.
	 */
	boolean saveLeft(String id, String data);
	
	/**
	 * Calculate diff between right and left data stored under specified ID.
	 * 
	 * @param id
	 *            ID under which data was stored previously.
	 * @return {@link DiffStatusDTO} object containing diff result information.
	 */
	DiffStatusDTO calcDiff(String id);
}
