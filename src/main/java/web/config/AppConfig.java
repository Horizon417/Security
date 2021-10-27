package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean(name = "entityManagerFactory")
   public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
      LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
      emf.setJpaVendorAdapter(getJpaVendorAdapter());
      emf.setDataSource(dataSource());
      emf.setPackagesToScan("web");
      emf.setJpaProperties(hibernateProperties());
      return emf;
   }

   @Bean
   public JpaVendorAdapter getJpaVendorAdapter() {
      return new HibernateJpaVendorAdapter();
   }

   @Bean(name = "transactionManager")
   public PlatformTransactionManager transactionManager() {
      return new JpaTransactionManager(
              getEntityManagerFactoryBean().getObject());
   }

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
      dataSource.setUrl(env.getRequiredProperty("db.url"));
      dataSource.setUsername(env.getRequiredProperty("db.username"));
      dataSource.setPassword(env.getRequiredProperty("db.password"));
      return dataSource;
   }

   private Properties hibernateProperties() {
      Properties properties = new Properties();
      properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
      properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
      properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
      return properties;
   }






}
