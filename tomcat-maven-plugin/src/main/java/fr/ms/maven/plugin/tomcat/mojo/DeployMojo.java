package fr.ms.maven.plugin.tomcat.mojo;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ms.tomcat.manager.TomcatPackagingManager;

@Mojo(name = "deploy", defaultPhase = LifecyclePhase.PACKAGE)
public class DeployMojo extends AbstractTomcatMojo {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(DeployMojo.class);

    /**
     * L'emplacement de la webapps. Par défaut cela prend la webapps eclaté se
     * trouvant dans le repertoire target
     */
    @Parameter(property = "tomcat.webapps.directory", defaultValue = "${project.build.directory}/${project.build.finalName}", required = true)
    private String webappsDirectory;

    @Override
    public void executeMojo(final TomcatPackagingManager tomcatDeployer) {
	LOG.info("L'application \"{}\" est en cours de deploiement a chaud", getWebappsPathName());
	tomcatDeployer.deploy(getWebappsPathName(), webappsDirectory);
	LOG.info("L'application \"{}\" est deployee sur : {}", getWebappsPathName(), webappsDirectory);

    }

}
