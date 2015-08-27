# Tomcat Maven Plugin

## Fonctionnalit�s g�n�rales
Cette librairie est un plug-in Maven permettant de deployer le r�pertoire Maven Target Webapp (property webappDirectory du plugin maven-war-plugin) dans un Tomcat en utilisant le Manager Tomcat

- Facile d'utilisation, il suffit de rajouter le "**pluginGroup**" dans votre fichier "**settings.xml**" et de lancer le plugin.
- Disponible sur le repository central de Maven.
- Compatible � partir de la version Java 6 et Maven 3.
- Compatible � partir de Tomcat 5.

## Installation rapide (� partir de Tomcat 7)
- Rajouter dans le fichier "**tomcat-users.xml**" qui se trouve dans le r�pertoire "**conf**" de votre Tomcat, l'acc�s � l'utilisateur "**admin**" pour le r�le "**manager-script**".

  

````xml
<role rolename="manager-script"/>
	<user username="admin" password="admin" roles="manager-script"/>
````

- D�marrer votre Tomcat

- Verifier si l'acc�s au manager fonctionne � l'aide d'un navigateur internet :

url      : http://localhost:8080/manager/text/list
user     : admin
password : admin

- Rajouter dans votre fichier "**settings.xml**" l'acc�s au plugin :

````xml
	<pluginGroups>
		<pluginGroup>com.github.marcosemiao.maven.plugins</pluginGroup>
	</pluginGroups>
```


## Utilisation rapide (� partir de Tomcat 7)
- **Pour deployer** la webapp, se mettre dans un projet Maven poss�dant un packaging "war" et taper ceci :

````
	mvn tomcat:deploy
```

- **Pour d�sintaller** la webapp, se mettre dans un projet Maven poss�dant un packaging "war" et taper ceci :

````
	mvn tomcat:undeploy
```

## Principe de Fonctionnement

Le plugin n'envoie par le fichier "war" au Tomcat mais indique � celui-ci ou se trouve le r�pertoire contenant la webapp.
Par cons�quent, ce plugin n'est utilisable qu'en "**localhost**"

Cela permet de modifier plus facilement des fichiers de la webapp en acc�dant directement au r�pertoire sans relivrer un fichier war � chaque fois.

Au lancement du plugin, celui-ci cr�e simplement un fichier "**context**" ayant comme nom le finalName de la webapp. Dans ce fichier, le plugin indique le r�pertoire contenant la webapp et le r�pertoire work pour cette m�me webapp. Ensuite ce fichier est transmis au Tomcat via le Tomcat Manager.

Exemple :

Prenons une webapp ayant comme finalName "subsonic".

- Le contexte Web de la Webapp a pour nom la property Maven : ${project.build.finalName}.
- Si le finalName est "subsonic", l'acc�s � la webapp se fera avec : http://localhost:8080/subsonic
- Le fichier context cr�� aura pour nom "**subsonic.xml**" et le contenu sera :

 
````xml
	<?xml version="1.0" encoding="UTF-8"?>
	<Context docBase="C:\Perso\dev\workspace\release-5.2.1\subsonic-main\target\subsonic" 	workDir="C:\Perso\dev\workspace\release-5.2.1\subsonic-main\target\subsonic-workDir" />
```


## Remarque

- Le plugin fonctionne uniquement avec le Tomcat lanc�.
- Quand une webapp est install�, elle le reste m�me si le serveur est arret� et redemarr�. Il n'est donc pas n�cessaire de deployer � chaque fois la webapp.
- Quand une webapp est install�, il n'est pas conseill� de "clean" le r�pertoire target pendant le fonctionnement du Tomcat, faites plutot un mvn install pour mettre � jour votre webapp.
- Si la webapp poss�de d�j� un fichier "**METAG-INF\context.xml**" celui-ci est utilis� par le plugin.


## Configuration

Le plugin est d�ja parametr� avec des parametres par d�faut :
Jusqu'� Tomcat 6  
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


## Installation rapide (jusqu'� Tomcat 6)
- Rajouter dans le fichier "**tomcat-users.xml**" qui se trouve dans le r�pertoire "**conf**" de votre Tomcat, l'acc�s � l'utilisateur "**admin**" pour le r�le "**manager-script**".

  

````xml
<role rolename="manager"/>
	<user username="admin" password="admin" roles="manager"/>
````

- D�marrer votre Tomcat

- Verifier si l'acc�s au manager fonctionne � l'aide d'un navigateur internet :

url      : http://localhost:8080/manager/list
user     : admin
password : admin

- Rajouter dans votre fichier "**settings.xml**" l'acc�s au plugin :

````xml
	<pluginGroups>
		<pluginGroup>com.github.marcosemiao.maven.plugins</pluginGroup>
	</pluginGroups>
```


## Utilisation rapide (jusqu'� Tomcat 6)
- **Pour deployer** la webapp, se mettre dans un projet Maven poss�dant un packaging "war" et taper ceci :

````
	mvn tomcat:deploy -Dmaven.tomcat.version=6
```

- **Pour d�sintaller** la webapp, se mettre dans un projet Maven poss�dant un packaging "war" et taper ceci :

````
	mvn tomcat:undeploy -Dmaven.tomcat.version=6
```