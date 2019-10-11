package br.uece.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.uece.entity.sqlserver.Aluno;
import br.uece.repository.sqlserver.AlunoRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = AlunoRepository.class, entityManagerFactoryRef = "sqlServerDSEmFactory", transactionManagerRef = "sqlServerDSTransactionManager")
public class SQLServerConfig {

	@Autowired
	private Environment env;
	
	@Bean("sqlServerDS")
	@Profile("production")
	public DataSource sqlServerDataSourceJNDI() {
		return new JndiDataSourceLookup().getDataSource(env.getProperty("sqlserver.datasource.jndi-name"));
	}

	@Bean
	@Profile("development")
	@ConfigurationProperties("sqlserver.datasource")
	public DataSourceProperties sqlServerDSProperties() {
		return new DataSourceProperties();
	}

	@Bean("sqlServerDS")
	@Profile("development")
	public DataSource sqlServerDS(@Qualifier("sqlServerDSProperties") DataSourceProperties sqlServerDSProperties) {
		return sqlServerDSProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean sqlServerDSEmFactory(@Qualifier("sqlServerDS") DataSource sqlServerDS,
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(sqlServerDS).packages(Aluno.class).persistenceUnit("sqlserver").build();
	}

	@Bean
	public PlatformTransactionManager sqlServerDSTransactionManager(
			@Qualifier("sqlServerDSEmFactory") EntityManagerFactory sqlServerDSEmFactory) {
		return new JpaTransactionManager(sqlServerDSEmFactory);
	}

}
