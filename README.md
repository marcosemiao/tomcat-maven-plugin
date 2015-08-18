# tomcat-maven-plugin
Deploy Webapp with Tomcat Manager

Installation :
1/
Rajouter le profil "manager-gui" à l'utilisateur "admin" dans le fichier tomcat-users.xml de votre Tomcat (répertoire conf) :

	<role rolename="manager-gui"/>
	<user username="admin" password="admin" roles="manager-script"/>
    
2/Demarrer le Tomcat (au moins la version 7)

3/verifier qu'on accède au manager avec l'url suivante :
http://localhost:8080/manager/text/list
user : admin
password : admin

4/ Rajouter dans le fichier settings.xml de Maven
	<pluginGroups>
		<pluginGroup>com.github.marcosemiao.maven.plugins</pluginGroup>
	</pluginGroups>
    


Utilisation :

pour installer l'application :

mvn clean install tomcat:deploy

Pour desintaller

mvn tomcat:undeploy


Remarque :

-Le plugin fonctionne uniquement avec Tomcat allumé
-Quand une application est installé, elle le reste même si le serveur est redemarré. Pour désinstaller, il faut faire un tomcat:undeploy (serveur allumé)

Pour chaque mise à jour de l'application, il suffit de faire un mvn install.

-Le contexte prend comme nom le nom de l'artificatId

Si le projet s'intitule "subsonic"

l'accès à l'application sera http://localhost:8080/subsonic

Ce plugin n'envoie pas le fichier war au tomcat, il spécifie au tomcat un repertoire target de maven. Il envoie le fichier context généré par le plugin 

Exemple :

Toujours avec l'application subsonic

le fichier contexte se nomme subsonic.xml

et le contenu est :

<?xml version="1.0" encoding="UTF-8"?>
<Context docBase="C:\Perso\dev\workspace\release-5.2.1\subsonic-main\target\subsonic" workDir="C:\Perso\dev\workspace\release-5.2.1\subsonic-main\target\subsonic-workDir" />




