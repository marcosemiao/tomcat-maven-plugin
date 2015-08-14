package fr.ms.tomcat.manager;

public interface TomcatManager {

    void setUrl(String url);

    void setUsername(String username);

    void setPassword(String password);

    int getVersion();

    void deploy(String warName, String DocBase);

    void undeploy(String warName);

    boolean isDeploy(String warName);
}
