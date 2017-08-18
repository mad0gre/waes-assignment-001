package victor.waes.dto;

/**
 * Diff status response data.
 */
public final class DiffStatusDTO {

	private int offset;
	private DiffStatus status;
	
	public DiffStatusDTO(int offset, DiffStatus status) {
		super();
		this.offset = offset;
		this.status = status;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public DiffStatus getStatus() {
		return status;
	}

	public void setStatus(DiffStatus status) {
		this.status = status;
	}

	/**
	 * Enumeration containing all possible results of a diff calculation. 
	 */
	public static enum DiffStatus {
		/** 
		 * Both sides are equal.
		 * */
		EQUAL, 
		
		/**
		 * Sides have the same size, but are not equal. Offset will contain index of
		 * first different byte.
		 **/
		NOT_EQUAL,
		
		/**
		 * Sides have different size.
		 */
		DIFFERENT_SIZE,
		
		/**
		 * No right data was provided for comparison.
		 */
		NO_RIGHT_DATA,
		
		/**
		 * No left data was provided for comparison.
		 */
		NO_LEFT_DATA,
	}
	
}
