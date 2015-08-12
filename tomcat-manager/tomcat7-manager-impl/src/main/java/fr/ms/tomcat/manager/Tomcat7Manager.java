package fr.ms.tomcat.manager;

import fr.ms.util.ServiceProvider;

@ServiceProvider
public class Tomcat7Manager implements TomcatManager {

    private String url = "http://localhost:8080/manager/text";

    private String username = "manager";

    private String password = "manager";

    public void setUrl(final String url) {
	this.url = url;

    }

    public void setUsername(final String username) {
	this.username = username;

    }

    public void setPassword(final String password) {
	this.password = password;

    }

    public void deploy(final String warName, final String DocBase) {
	try {
	    final TomcatManagerImpl tomcatManager = new TomcatManagerImpl(url, username, password);
	    tomcatManager.deploy(warName, DocBase);
	} catch (final Exception e) {
	    throw new TomcatManagerException(e);
	}
    }

    public void undeploy(final String warName) {
	try {
	    final TomcatManagerImpl tomcatManager = new TomcatManagerImpl(url, username, password);
	    tomcatManager.undeploy(warName);
	} catch (final Exception e) {
	    throw new TomcatManagerException(e);
	}
    }

    public boolean isDeploy(final String warName) {
	try {
	    final TomcatManagerImpl tomcatManager = new TomcatManagerImpl(url, username, password);
	    return tomcatManager.isDeploy(warName);
	} catch (final Exception e) {
	    throw new TomcatManagerException(e);
	}
    }

    public int getVersion() {
	return 7;
    }
}
