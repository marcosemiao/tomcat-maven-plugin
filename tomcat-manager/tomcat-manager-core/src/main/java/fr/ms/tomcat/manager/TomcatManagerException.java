package fr.ms.tomcat.manager;

public class TomcatManagerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TomcatManagerException() {
    }

    public TomcatManagerException(final String message) {
        super(message);
    }

    public TomcatManagerException(final Throwable cause) {
        super(cause);
    }

    public TomcatManagerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}