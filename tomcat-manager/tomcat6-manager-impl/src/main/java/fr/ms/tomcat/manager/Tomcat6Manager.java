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
package fr.ms.tomcat.manager;

import fr.ms.util.ServiceLoader;

/**
 *
 * @see <a href="http://marcosemiao4j.wordpress.com">Marco4J</a>
 *
 *
 * @author Marco Semiao
 *
 */
@ServiceLoader
public class Tomcat6Manager implements TomcatManager {

    private String url = "http://localhost:8080/manager";

    private String username = "admin";

    private String password = "admin";

    @Override
    public void setUrl(final String url) {
	this.url = url;

    }

    @Override
    public void setUsername(final String username) {
	this.username = username;

    }

    @Override
    public void setPassword(final String password) {
	this.password = password;

    }

    @Override
    public void deploy(final String warName, final String DocBase) {
	try {
	    final TomcatManagerImpl tomcatManager = new TomcatManagerImpl(url, username, password);
	    tomcatManager.deploy(warName, DocBase);
	} catch (final Exception e) {
	    throw new TomcatManagerException(e);
	}
    }

    @Override
    public void undeploy(final String warName) {
	try {
	    final TomcatManagerImpl tomcatManager = new TomcatManagerImpl(url, username, password);
	    tomcatManager.undeploy(warName);
	} catch (final Exception e) {
	    throw new TomcatManagerException(e);
	}
    }

    @Override
    public boolean isDeploy(final String warName) {
	try {
	    final TomcatManagerImpl tomcatManager = new TomcatManagerImpl(url, username, password);
	    return tomcatManager.isDeploy(warName);
	} catch (final Exception e) {
	    throw new TomcatManagerException(e);
	}
    }

    @Override
    public int getVersion() {
	return 6;
    }
}
