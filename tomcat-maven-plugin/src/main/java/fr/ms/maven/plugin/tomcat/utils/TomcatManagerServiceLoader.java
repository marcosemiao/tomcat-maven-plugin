package fr.ms.maven.plugin.tomcat.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

import fr.ms.tomcat.manager.TomcatManager;

public class TomcatManagerServiceLoader {

    public final static TomcatManager createService(final Integer version) {
	final ServiceLoader<TomcatManager> load = ServiceLoader.load(TomcatManager.class);

	final List<TomcatManager> services = new ArrayList<TomcatManager>();
	for (final TomcatManager service : load) {
	    if (version != null && service.getVersion() == version) {
		return service;
	    }
	    services.add(service);
	}

	return Collections.max(services, new Comparator<TomcatManager>() {

	    @Override
	    public int compare(final TomcatManager o1, final TomcatManager o2) {
		return o1.getVersion() - o2.getVersion();
	    }
	});
    }
}
