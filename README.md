# Projeto Base Spring Boot 2

O projeto está pré-configurado para acessar dois banco de dados e utilizar o pool de conexões do tomcat em perfil de produção (production). O projeto possui um exemplo básico.

**Orientação para execução do projeto:**

*  Alterar as propriedades de conexão dos arquivos *application-development.properties* e *application-production.properties*.

*  O arquivo *application.properties* possui uma propriedade chamada **spring.profiles.active**. Essa pode assumir dois valores:
      * **development** - Configura o projeto com perfil de desenvolvimento. Assim, o Spring lê as propriedades de conexão diretamente do arquivo *application-development.properties* possibilitando utilizar o tomcat embutido no Spring boot.
      * **production** - Configura o projeto com perfil de produção. Permitindo que o spring leia as propriedades do arquivo application-production.properties e via JNDI acesse o pool de conexões do tomcat no qual o deploy do War será realizado.
      
      Exemplos de datasources para ambiente de produção (context.xml):
                
        <!-- SQL Server -->
        <Resource auth="Container" driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver" validationQuery="select 1"
        maxActive="2" maxIdle="2" maxWait="-1" name="jdbc/ALIAS" username="USUARIO"	password="SENHA" 
        type="javax.sql.DataSource" url="jdbc:sqlserver://IP_BANCO:PORTA;databaseName=NOME_BANCO" />
        	
        <!-- PostgreSQL -->
        <Resource auth="Container" driverClassName="org.postgresql.Driver" validationquery="SELECT 1" 
	    maxActive="2" maxIdle="2" maxWait="-1"	name="jdbc/ALIAS"	
	    type="javax.sql.DataSource" url="jdbc:postgresql://IP_BANCO:PORTA/NOME_BANCO" username="USUARIO" password="SENHA" />
      

OBS: Antes de gerar o WAR para ambiente de Homologação ou produção, lembre-se de alterar a propriedade **spring.profiles.active** para **production** (*spring.profiles.active=production*) em *application.properties*
