package org.common1;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		Bundle bundle = FrameworkUtil.getBundle(Activator.class);
		String bundleLocation = (bundle != null) ? bundle.getLocation() : "";

		System.out.println(getClass().getName() + ".start() bundleLocation=" + bundleLocation);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;

		Bundle bundle = FrameworkUtil.getBundle(Activator.class);
		String bundleLocation = (bundle != null) ? bundle.getLocation() : "";

		System.out.println(getClass().getName() + ".stop() bundleLocation=" + bundleLocation);
	}

}
