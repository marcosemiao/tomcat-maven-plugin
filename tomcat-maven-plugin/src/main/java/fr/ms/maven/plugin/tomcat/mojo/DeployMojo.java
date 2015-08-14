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
package fr.ms.maven.plugin.tomcat.mojo;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ms.tomcat.manager.TomcatManager;

/**
 *
 * @see <a href="http://marcosemiao4j.wordpress.com">Marco4J</a>
 *
 *
 * @author Marco Semiao
 *
 */
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
    public void executeMojo(final TomcatManager tomcatDeployer) {
	LOG.info("L'application \"{}\" est en cours de deploiement a chaud", getWebappsPathName());
	tomcatDeployer.deploy(getWebappsPathName(), webappsDirectory);
	LOG.info("L'application \"{}\" est deployee sur : {}", getWebappsPathName(), webappsDirectory);

    }

}
