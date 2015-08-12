package fr.ms.maven.plugin.tomcat.mojo;

import java.net.URL;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

import fr.ms.maven.plugin.AbstractLoggerMojo;
import fr.ms.maven.plugin.tomcat.utils.TomcatManagerServiceLoader;
import fr.ms.tomcat.manager.TomcatManager;

public abstract class AbstractTomcatMojo extends AbstractLoggerMojo {

    /**
     * L'url d'accès vers le manager tomcat. Exemple :
     * http://localhost:8080/manager/html sous tomcat 7
     */
    @Parameter(property = "maven.tomcat.url")
    private URL url;

    /**
     * Le compte de connexion vers le manager tomcat Exemple : admin
     */
    @Parameter(property = "maven.tomcat.username")
    private String username;

    /**
     * Le mot de passe de connexion vers le manager tomcat Exemple : admin
     */
    @Parameter(property = "maven.tomcat.password")
    private String password;

    /**
     * Version tomcat
     */
    @Parameter(property = "maven.tomcat.version")
    private Integer tomcatVersion;

    /**
     * Le type de module Cela correspond à la balise <type> du pom.xml courant
     */
    @Parameter(defaultValue = "${project.packaging}", required = true)
    private String packaging;

    /**
     * Le nom de la webapps
     */
    @Parameter(property = "maven.tomcat.context", defaultValue = "${project.build.finalName}", required = true)
    String webappsContext;

    String getWebappsPathName() {
	final String webappsPathName = "/" + webappsContext;

	return webappsPathName;
    }

    public abstract void executeMojo(final TomcatManager tomcatDeployer);

    public void execute() throws MojoExecutionException, MojoFailureException {

	final TomcatManager service = TomcatManagerServiceLoader.createService(tomcatVersion);

	if (url != null) {
	    service.setUrl(url.toExternalForm());
	}

	if (username != null) {
	    service.setUsername(username);
	}

	if (password != null) {
	    service.setPassword(password);
	}

	executeMojo(service);
    }
}