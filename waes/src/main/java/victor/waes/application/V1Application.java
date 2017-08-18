package victor.waes.application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import victor.waes.business.DiffBusiness;
import victor.waes.business.impl.DiffBusinessSimpleImpl;
import victor.waes.resource.DiffResource;

/**
 * Application implementation for the V1 API.
 */
public final class V1Application extends ResourceConfig {

	public V1Application() {
		register(DiffResource.class);
		
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(new DiffBusinessSimpleImpl()).to(DiffBusiness.class);
			}
		});
	}
	
}
