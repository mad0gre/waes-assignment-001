package victor.waes.application;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.core.Application;

import victor.waes.resource.DiffResource;

/**
 * Application implementation for the V1 API.
 */
public final class V1Application extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		return Collections.singleton(DiffResource.class);
	}
	
}
