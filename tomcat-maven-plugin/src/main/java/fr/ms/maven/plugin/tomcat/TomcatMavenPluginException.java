package fr.ms.maven.plugin.tomcat;

public class TomcatMavenPluginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TomcatMavenPluginException() {
    }

    public TomcatMavenPluginException(final String message) {
        super(message);
    }

    public TomcatMavenPluginException(final Throwable cause) {
        super(cause);
    }

    public TomcatMavenPluginException(final String message, final Throwable cause) {
        super(message, cause);
    }
}