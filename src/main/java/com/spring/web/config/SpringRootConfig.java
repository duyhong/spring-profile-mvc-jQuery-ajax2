package com.spring.web.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//This is equivalent to spring-hibernate5-conf.xml, spring-dao.xml, and spring-service.xml

@EnableTransactionManagement	//<tx:annotation/>
@Configuration
@ComponentScan(basePackages = { "com.spring.web.mvc.dao","com.spring.web.mvc.service"})
public class SpringRootConfig {

	/*@Autowired
	private ApplicationContext context;*/
	
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
	    sessionBuilder.scanPackages("com.spring.web.mvc.dao.entity");
	    //sessionBuilder.addAnnotatedClasses(CustomerEntity.class,CustomerHistoryEntity.class,CityEntity.class);
	    sessionBuilder.setProperty("hibernate.show_sql", "true");
	    sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    sessionBuilder.setProperty("hibernate.hbm2ddl.auto", "update");
	   // sessionBuilder.setProperties(getHibernateProperties());
	    return sessionBuilder.buildSessionFactory();
	}

	/*@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
		factoryBean.setAnnotatedClasses(CustomerEntity.class,CustomerHistoryEntity.class,CityEntity.class);
		return factoryBean;
	}*/

	//<bean id
	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		//jdbc/cpool = META-INF/context.xml
		//java:comp/env/ - this fix for tomcat JNDI lookup
		DataSource dataSource = dataSourceLookup.getDataSource("java:comp/env/jdbc/cpool");
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory());
		//transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
}
