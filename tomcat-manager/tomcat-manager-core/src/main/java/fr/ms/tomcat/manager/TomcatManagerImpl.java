package fr.ms.tomcat.manager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.jdom2.JDOMException;

public class TomcatManagerImpl {

    /**
     * l'url du manager tomcat
     */
    private final URL url;

    /**
     * l'utilisateur pour le Manager Tomcat
     */
    private final String username;

    /**
     * le mot de passe pour le Manager Tomcat
     */
    private final String password;

    /**
     * encodage de l'appel
     */
    private final String charset;

    public TomcatManagerImpl(final URL url, final String username, final String password, final String charset) {
	this.url = url;
	this.username = username;
	this.password = password;
	this.charset = charset;
    }

    public TomcatManagerImpl(final URL url, final String username, final String password) {
	this(url, username, password, "UTF-8");
    }

    public TomcatManagerImpl(final String url, final String username, final String password, final String charset) throws MalformedURLException {
	this(new URL(url), username, password, charset);
    }

    public TomcatManagerImpl(final String url, final String username, final String password) throws MalformedURLException {
	this(url, username, password, "UTF-8");
    }

    public String list() throws IOException, TomcatManagerException {
	final StringBuilder urlBuffer = new StringBuilder(url.toString());
	urlBuffer.append("/list");

	final String reponse = TomcatManagerUrl.appelUrl(urlBuffer.toString(), username, password, charset);

	return reponse;
    }

    public boolean isDeploy(final String path) throws IOException, TomcatManagerException {
	final StringBuilder urlBuffer = new StringBuilder(url.toString());
	urlBuffer.append("/sessions");
	urlBuffer.append("?path=");
	urlBuffer.append(path);

	final String reponse = TomcatManagerUrl.appelUrl(urlBuffer.toString(), username, password, charset);

	if (reponse.startsWith("OK -")) {
	    return true;
	} else {
	    return false;
	}
    }

    public void undeploy(final String path) throws IOException, TomcatManagerException {

	final StringBuilder urlBuffer = new StringBuilder(url.toString());
	urlBuffer.append("/undeploy");
	urlBuffer.append("?path=");
	urlBuffer.append(URLEncoder.encode(path, this.charset));

	TomcatManagerUrl.appelUrl(urlBuffer.toString(), username, password, charset);
    }

    public void deploy(final String path, final String docBase) throws JDOMException, IOException, TomcatManagerException {

	final String fichierWebAppsContextName = docBase + File.separator + "META-INF" + File.separator + "context.xml";

	final File fichierContext = ContextFileUtils.creationFichierContextDocBase(fichierWebAppsContextName, docBase);

	// Creation des parametres http get

	final StringBuilder urlBuffer = new StringBuilder(url.toString());
	urlBuffer.append("/deploy");
	urlBuffer.append("?path=");
	urlBuffer.append(URLEncoder.encode(path, this.charset));
	urlBuffer.append("&config=file:");
	urlBuffer.append(URLEncoder.encode(fichierContext.getPath(), this.charset));

	TomcatManagerUrl.appelUrl(urlBuffer.toString(), username, password, charset);

	fichierContext.delete();
    }
}
