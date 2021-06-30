package com.domiworld.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@MapperScan(basePackages = "com.domiworld.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceMybatisConfig {
	@Autowired
    private Environment env;
	
	@Bean
	@Primary
	public DataSource mybatisDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
	}
	
	@Bean(name="sqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactory(
			@Qualifier("mybatisDataSource") DataSource dataSource,
			ApplicationContext applicationContext) throws Exception {
		
		log.info("SQL DATASOURCE : {}", dataSource);
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**/*.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name = "sqlSession")
	@Primary
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
