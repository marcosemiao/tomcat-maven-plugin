# Tomcat Maven Plugin

## Fonctionnalités générales
Cette librairie est un plug-in Maven permettant de deployer le répertoire Maven Target Webapp (property webappDirectory du plugin maven-war-plugin) dans un Tomcat en utilisant le Manager Tomcat

- Facile d'utilisation, il suffit de rajouter le "**pluginGroup**" dans votre fichier "**settings.xml**" et de lancer le plugin.
- Disponible sur le repository central de Maven.
- Compatible à partir de la version Java 6 et Maven 3.
- Compatible à partir de Tomcat 5.

## Installation rapide (à partir de Tomcat 7)
- Rajouter dans le fichier "**tomcat-users.xml**" qui se trouve dans le répertoire "**conf**" de votre Tomcat, l'accès à l'utilisateur "**admin**" pour le rôle "**manager-script**".

  

````xml
<role rolename="manager-script"/>
	<user username="admin" password="admin" roles="manager-script"/>
````

- Démarrer votre Tomcat

- Verifier si l'accès au manager fonctionne à l'aide d'un navigateur internet :

url      : http://localhost:8080/manager/text/list
user     : admin
password : admin

- Rajouter dans votre fichier "**settings.xml**" l'accès au plugin :

````xml
	<pluginGroups>
		<pluginGroup>com.github.marcosemiao.maven.plugins</pluginGroup>
	</pluginGroups>
```


## Utilisation rapide (à partir de Tomcat 7)
- **Pour deployer** la webapp, se mettre dans un projet Maven possèdant un packaging "war" et taper ceci :

````
	mvn tomcat:deploy
```

- **Pour désintaller** la webapp, se mettre dans un projet Maven possèdant un packaging "war" et taper ceci :

````
	mvn tomcat:undeploy
```

## Principe de Fonctionnement

Le plugin n'envoie par le fichier "war" au Tomcat mais indique à celui-ci ou se trouve le répertoire contenant la webapp.
Par conséquent, ce plugin n'est utilisable qu'en "**localhost**"

Cela permet de modifier plus facilement des fichiers de la webapp en accèdant directement au répertoire sans relivrer un fichier war à chaque fois.

Au lancement du plugin, celui-ci crée simplement un fichier "**context**" ayant comme nom le finalName de la webapp. Dans ce fichier, le plugin indique le répertoire contenant la webapp et le répertoire work pour cette même webapp. Ensuite ce fichier est transmis au Tomcat via le Tomcat Manager.

Exemple :

Prenons une webapp ayant comme finalName "subsonic".

- Le contexte Web de la Webapp a pour nom la property Maven : ${project.build.finalName}.
- Si le finalName est "subsonic", l'accès à la webapp se fera avec : http://localhost:8080/subsonic
- Le fichier context créé aura pour nom "**subsonic.xml**" et le contenu sera :

 
````xml
	<?xml version="1.0" encoding="UTF-8"?>
	<Context docBase="C:\Perso\dev\workspace\release-5.2.1\subsonic-main\target\subsonic" 	workDir="C:\Perso\dev\workspace\release-5.2.1\subsonic-main\target\subsonic-workDir" />
```


## Remarque

- Le plugin fonctionne uniquement avec le Tomcat lancé.
- Quand une webapp est installé, elle le reste même si le serveur est arreté et redemarré. Il n'est donc pas nécessaire de deployer à chaque fois la webapp.
- Quand une webapp est installé, il n'est pas conseillé de "clean" le répertoire target pendant le fonctionnement du Tomcat, faites plutot un mvn install pour mettre à jour votre webapp.
- Si la webapp possède déjà un fichier "**METAG-INF\context.xml**" celui-ci est utilisé par le plugin.


## Configuration

Le plugin est déja parametré avec des parametres par défaut :
Jusqu'à Tomcat 6  
	URL			: http://localhost:8080/manager
A partir de Tomcat 7 
	URL			: http://localhost:8080/manager/text

Pour toutes les versions :
	USER		: admin
	PASSWORD	: admin
	
Il est possible de modifier ces valeurs en ligne de commande en utilisant :

| Option | property |
| ------ | ------- |
| URL | maven.tomcat.url |
| USER | maven.tomcat.username |
| PASSWORD | maven.tomcat.password |


## Installation rapide (jusqu'à Tomcat 6)
- Rajouter dans le fichier "**tomcat-users.xml**" qui se trouve dans le répertoire "**conf**" de votre Tomcat, l'accès à l'utilisateur "**admin**" pour le rôle "**manager-script**".

  

````xml
<role rolename="manager"/>
	<user username="admin" password="admin" roles="manager"/>
````

- Démarrer votre Tomcat

- Verifier si l'accès au manager fonctionne à l'aide d'un navigateur internet :

url      : http://localhost:8080/manager/list
user     : admin
password : admin

- Rajouter dans votre fichier "**settings.xml**" l'accès au plugin :

````xml
	<pluginGroups>
		<pluginGroup>com.github.marcosemiao.maven.plugins</pluginGroup>
	</pluginGroups>
```


## Utilisation rapide (jusqu'à Tomcat 6)
- **Pour deployer** la webapp, se mettre dans un projet Maven possèdant un packaging "war" et taper ceci :

````
	mvn tomcat:deploy -Dmaven.tomcat.version=6
```

- **Pour désintaller** la webapp, se mettre dans un projet Maven possèdant un packaging "war" et taper ceci :

````
	mvn tomcat:undeploy -Dmaven.tomcat.version=6
```