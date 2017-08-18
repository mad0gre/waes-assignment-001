package victor.waes.bean;

/**
 * Response bean used on Diff Resource.
 */
public final class DiffResponseBean {
	
	private String status;
	private Integer offset;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
}
