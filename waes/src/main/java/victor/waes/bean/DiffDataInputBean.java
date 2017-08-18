package victor.waes.bean;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import victor.waes.bean.validation.Base64;

/**
 * Input data bean used on Diff Resource. 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DiffDataInputBean {
	
	@NotNull
	@Base64
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
