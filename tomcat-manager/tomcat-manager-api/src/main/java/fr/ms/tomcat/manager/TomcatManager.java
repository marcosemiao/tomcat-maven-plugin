package fr.ms.tomcat.manager;

public interface TomcatManager {

    void setUrl(String url);

    void setUsername(String username);

    void setPassword(String password);

    int getVersion();

    /**
     * Deploiement d'une webapp dans le serveur d'application
     * 
     * @param warName
     *            le nom de la webapps (cela sera utilisé pour le context)
     * @param DocBase
     *            le répertoire se trouvant la webapps éclaté
     * @throws TomcatDeployerException
     *             si erreur
     */
    void deploy(String warName, String DocBase);

    /**
     * Retire la webapp du serveur d'application
     * 
     * @param warName
     *            le nom de la webapps (cela sera utilisé pour le context)
     * @throws TomcatDeployerException
     *             si erreur
     */
    void undeploy(String warName);

    /**
     * Verifie si la webapp est présente dans le serveur d'application
     * 
     * @param warName
     *            le nom de la webapps (cela sera utilisé pour le context)
     * @return true : si l'application existe déjà - false : si l'application
     *         est inconnue
     * @throws TomcatDeployerException
     *             si erreur
     */
    boolean isDeploy(String warName);
}
