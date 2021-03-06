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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @see <a href="http://marcosemiao4j.wordpress.com">Marco4J</a>
 *
 *
 * @author Marco Semiao
 *
 */
public final class ContextFileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ContextFileUtils.class);

    private ContextFileUtils() {
    }

    public static File creationFichierContextDocBase(final String fichierContext, final String webappDirectory)
	    throws JDOMException, IOException {
	final File fileFichierContext = new File(fichierContext);
	final File fileWebappDirectory = new File(webappDirectory);

	return creationFichierContextDocBase(fileFichierContext, fileWebappDirectory);

    }

    public static File creationFichierContextDocBase(final File fichierContext, final File webappDirectory)
	    throws JDOMException, IOException {

	Element context = null;
	Document document = null;

	if (fichierContext.exists()) {
	    LOG.debug("Le fichier context existe déjà dans la webapps : {}.", fichierContext.getAbsolutePath());
	    final SAXBuilder sxb = new SAXBuilder();
	    document = sxb.build(fichierContext);
	    context = document.getRootElement();
	} else {
	    LOG.debug("Le fichier context n'existe pas dans la webapps : {}.", fichierContext.getAbsolutePath());
	    context = new Element("Context");
	    document = new Document(context);
	}

	final Attribute docBase = new Attribute("docBase", webappDirectory.getAbsolutePath());
	final Attribute workDir = new Attribute("workDir", webappDirectory.getAbsolutePath() + "-workDir");
	context.setAttribute(docBase);
	context.setAttribute(workDir);

	final XMLOutputter xmlContextFile = new XMLOutputter(Format.getPrettyFormat());

	if (LOG.isDebugEnabled()) {
	    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    xmlContextFile.output(document, baos);

	    LOG.debug(baos.toString());
	}

	final File fichierContextTemporaire = File.createTempFile("contextTomcat", ".xml");

	final FileOutputStream fichierContextTemporaireOutputStream = new FileOutputStream(fichierContextTemporaire);

	xmlContextFile.output(document, fichierContextTemporaireOutputStream);
	fichierContextTemporaireOutputStream.close();
	return fichierContextTemporaire;
    }
}
