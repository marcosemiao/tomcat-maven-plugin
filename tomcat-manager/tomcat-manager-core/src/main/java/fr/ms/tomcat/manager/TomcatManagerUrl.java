package fr.ms.tomcat.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TomcatManagerUrl {

    private static final Logger LOG = LoggerFactory.getLogger(TomcatManagerUrl.class);

    private TomcatManagerUrl() {
    }

    public static String appelUrl(final String url, final String username, final String password, final String charset)
	    throws MalformedURLException {

	LOG.debug("Parametre http get : " + url.toString());

	final URL urlTomcat = new URL(url);
	return appelUrl(urlTomcat, username, password, charset);
    }

    public static String appelUrl(final URL url, final String username, final String password, final String charset) {

	try {
	    final URLConnection urlTomcatConnection = url.openConnection();

	    final HttpURLConnection connection = (HttpURLConnection) urlTomcatConnection;

	    LOG.debug("url : " + url);
	    connection.setAllowUserInteraction(false);
	    connection.setDoInput(true);
	    connection.setUseCaches(false);

	    connection.setDoOutput(false);
	    connection.setRequestMethod("GET");

	    connection.setRequestProperty("Authorization", toAuthorization(username, password));

	    connection.connect();

	    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
		if (connection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
		    throw new TomcatManagerException("Reponse http : " + connection.getResponseMessage()
			    + " - Les droits \"manager\" ne sont pas appliques - utilisateur : \"" + username
			    + "\" password : \"" + password + "\"");
		}
		throw new TomcatManagerException("HttpURLConnection.HTTP_UNAUTHORIZED");
	    }

	    final String response = toString(connection.getInputStream(), charset);

	    LOG.debug("reponse : " + response);

	    return response;
	} catch (final Exception e) {
	    throw new TomcatManagerException("L'url du manager tomcat est peut etre incorrecte : \"" + url + "\"", e);
	}
    }

    private static String toAuthorization(final String username, final String password) {
	final StringBuilder buffer = new StringBuilder();
	buffer.append(username).append(':');
	if (password != null) {
	    buffer.append(password);
	}
	return "Basic " + new String(Base64.encodeBase64(buffer.toString().getBytes()));
    }

    private static String toString(final InputStream in, final String charset) throws IOException {
	final InputStreamReader reader = new InputStreamReader(in, charset);

	final StringBuffer buffer = new StringBuffer();
	final char[] chars = new char[1024];
	int n;
	while ((n = reader.read(chars, 0, chars.length)) != -1) {
	    buffer.append(chars, 0, n);
	}

	return buffer.toString();
    }
}
