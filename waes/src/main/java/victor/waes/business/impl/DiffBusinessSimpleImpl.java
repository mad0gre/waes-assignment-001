package victor.waes.business.impl;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import victor.waes.business.DiffBusiness;
import victor.waes.dto.DiffStatusDTO;
import victor.waes.dto.DiffStatusDTO.DiffStatus;

/**
 * Simple implementation of the Diff Business interface. This stores data in
 * maps in memory.
 */
public final class DiffBusinessSimpleImpl implements DiffBusiness {
	
	private static final Charset CHARSET = Charset.forName("ISO-8859-1");
	
	private final Map<String, byte[]> rightMap = new HashMap<>();
	private final Map<String, byte[]> leftMap = new HashMap<>();
	
	@Override
	public boolean saveRight(String id, String data) {
		return decodeAndStoreData(id, data, rightMap);
	}

	@Override
	public boolean saveLeft(String id, String data) {
		return decodeAndStoreData(id, data, leftMap);
	}

	@Override
	public DiffStatusDTO calcDiff(String id) {
		DiffStatus status = null;
		int offset = 0;
		
		byte[] right = rightMap.get(id);
		byte[] left = leftMap.get(id);
		
		// Look for differences in between both byte arrays.
		if (right == null) {
			status = DiffStatus.NO_RIGHT_DATA;
		} else if (left == null) {
			status = DiffStatus.NO_LEFT_DATA;
		} else if (right.length != left.length) {
			status = DiffStatus.DIFFERENT_SIZE;
		} else {
			for (int i = 0; i < right.length; i++) {
				if (right[i] != left[i]) {
					offset = i;
					status = DiffStatus.NOT_EQUAL;
				}
			}
		}

		// No difference was found, so both arrays are equal.
		if (status == null) {
			status = DiffStatus.EQUAL;
		}
		
		return new DiffStatusDTO(offset, status);
	}
	
	private boolean decodeAndStoreData(String id, String data, Map<String, byte[]> map) {
		// Decode Base 64 data.
		byte[] bytes = null;
		
		try {
			bytes = Base64.getDecoder().decode(data.getBytes(CHARSET));
		} catch (IllegalArgumentException e) {
			// Nothing to do.
		}
		
		if (bytes != null && map != null) {
			map.put(id, bytes);
			return true;
		}
		
		return false;
	}

}
