# Spring Boot 2 Base Project

The project is preconfigured to access two databases and use the tomcat connection pool in production profile. The project has a basic example.

**Project implementation guidelines:**

*  Change the connection properties of the *application-development.properties* and *application-production.properties* files.

*  The *application.properties* file has a property called **spring.profiles.active** that can take two values:

      * **development** - Configures the project with development profile. Thus, Spring reads the connection properties directly from the *application-development.properties* file, making it possible to use Spring Boot built-in tomcat.
      * **production** - Configures the project with production profile. Allowing spring to read the properties of the application-production.properties file and via JNDI to access the tomcat connection pool where War will be deployed.
      
      Examples of datasources for production environment (context.xml):
      
``` xml
<!-- SQL Server -->
<Resource auth="Container" driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
	validationQuery="select 1" maxActive="2" maxIdle="2" maxWait="-1" name="jdbc/ALIAS" 
	url="jdbc:sqlserver://IP_DATABASE:PORT;databaseName=NAME_DATABASE"
	username="USER" password="PASSWORD" type="javax.sql.DataSource" />
```      

``` xml
<!-- PostgreSQL -->
<Resource auth="Container" driverClassName="org.postgresql.Driver" validationquery="SELECT 1" 
	maxActive="2" maxIdle="2" maxWait="-1" name="jdbc/ALIAS"	
	type="javax.sql.DataSource" url="jdbc:postgresql://IP_DATABASE:PORT/NAME_DATABASE"
	username="USER" password="PASSWORD" />
```    


NOTE: Before generating the WAR for Homologation or production environment, be sure to change the **spring.profiles.active** property to **production** (*spring.profiles.active=production*) in *application.properties*
