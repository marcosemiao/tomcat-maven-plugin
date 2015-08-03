package fr.ms.tomcat.manager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextFileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ContextFileUtils.class);

    private ContextFileUtils() {
        // Rien
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
