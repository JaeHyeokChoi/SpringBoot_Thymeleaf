package com.domiworld.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import lombok.extern.slf4j.Slf4j;
import javax.sql.DataSource;

@Configuration
@Slf4j
@EnableJpaRepositories(
        entityManagerFactoryRef = "jpaEntityManagerFactory"
        , transactionManagerRef = "jpaTransactionManager"
        , basePackages = "com.domiWorld.repository" // repository 위치
)
@EnableTransactionManagement
@MapperScan(basePackages = "com.domiworld.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class ContextConfig {
	@Autowired
    private Environment env;
	
	//https://jforj.tistory.com/91?category=853995
	
	// JPA - h2 DB정보
    @Bean
    public DataSource jpaDataSource() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource2.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource2.url"));
        dataSource.setUsername(env.getProperty("spring.datasource2.username"));
        dataSource.setPassword(env.getProperty("spring.datasource2.password"));
        
        return dataSource;
    }
    
    // mybatis - postgresql DB정보
    @Bean
	public DataSource mybatisDataSource() throws Exception{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource1.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource1.url"));
        dataSource.setUsername(env.getProperty("spring.datasource1.username"));
        dataSource.setPassword(env.getProperty("spring.datasource1.password"));
        
        return dataSource;
	}
    
    // mybatis - sqlSessionFactory 설정
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(
			ApplicationContext applicationContext) throws Exception {
		
		log.info("SQL DATASOURCE : {}", mybatisDataSource());
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mybatisDataSource());
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**/*.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	// mybatis - sqlSession 설정
	@Bean(name = "sqlSession")
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory);
	}
    
	// jpa 설정
    @Bean
    public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory () throws Exception{
    	LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    	jpaEntityManagerFactory.setDataSource(jpaDataSource());
    	jpaEntityManagerFactory.setPersistenceUnitName("jpa-h2");
    	jpaEntityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());    	
    	return jpaEntityManagerFactory;
    }

    // transactional 설정
    @Bean
    public PlatformTransactionManager jpaTransactionManager() throws Exception{
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(jpaEntityManagerFactory().getObject());
        
        DataSourceTransactionManager mybaitsTransactionManager = new DataSourceTransactionManager();
        mybaitsTransactionManager.setDataSource(mybatisDataSource());
        
        ChainedTransactionManager transactionManager = new ChainedTransactionManager(jpaTransactionManager, mybaitsTransactionManager);
        return transactionManager;
    }
}
