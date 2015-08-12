package fr.ms.maven.plugin.tomcat.mojo;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ms.tomcat.manager.TomcatManager;

@Mojo(name = "undeploy", defaultPhase = LifecyclePhase.INITIALIZE)
public class UnDeployMojo extends AbstractTomcatMojo {

    private static final Logger LOG = LoggerFactory.getLogger(UnDeployMojo.class);

    @Override
    public void executeMojo(final TomcatManager tomcatDeployer) {
	if (tomcatDeployer.isDeploy(getWebappsPathName())) {
	    tomcatDeployer.undeploy(getWebappsPathName());
	    LOG.info("L'application \"{}\" est supprimee", getWebappsPathName());
	} else {
	    LOG.warn("L'application \"{}\" n'existe pas", getWebappsPathName());
	}
    }

}
