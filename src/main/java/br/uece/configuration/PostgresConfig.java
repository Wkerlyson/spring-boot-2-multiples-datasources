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
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.uece.entity.postgres.Lei;
import br.uece.repository.postgres.LeiRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = LeiRepository.class, entityManagerFactoryRef = "postgresDSEmFactory", transactionManagerRef = "postgresDSTransactionManager")
public class PostgresConfig {
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean("postgresDS")
	@Profile("production")
	public DataSource postgresDataSourceJNDI() { 
		return new JndiDataSourceLookup().getDataSource(env.getProperty("postgres.datasource.jndi-name"));
	}

	@Primary
	@Bean
	@Profile("development")
	@ConfigurationProperties("postgres.datasource")
	public DataSourceProperties postgresDSProperties() {
		return new DataSourceProperties();
	}
	
	
	@Primary
	@Bean("postgresDS")
	@Profile("development")
	public DataSource postgresDS(@Qualifier("postgresDSProperties") DataSourceProperties postgresDSProperties) {
		return postgresDSProperties.initializeDataSourceBuilder().build();
	}
	
	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean postgresDSEmFactory(@Qualifier("postgresDS") DataSource postgresDS, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(postgresDS).packages(Lei.class).persistenceUnit("postgres").build();
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager postgresDSTransactionManager(@Qualifier("postgresDSEmFactory") EntityManagerFactory postgresDSEmFactory) {
		return new JpaTransactionManager(postgresDSEmFactory);
	}

}
