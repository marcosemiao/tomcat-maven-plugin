/*
 * Copyright 2015 Marco Semiao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package fr.ms.maven.plugin.tomcat.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

import fr.ms.tomcat.manager.TomcatManager;

/**
 *
 * @see <a href="http://marcosemiao4j.wordpress.com">Marco4J</a>
 *
 *
 * @author Marco Semiao
 *
 */
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
