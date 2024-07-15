package com.example.demo.configurator;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Configuration
@Component
public class MySQLConfig {

    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource1() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/football_league")
                .username("root")
                .password("LoA12Kie17")
                .build();
    }

    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") final DataSource dataSource1) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource1);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis-mapper-xml/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate1")
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory1") final SqlSessionFactory sqlSessionFactory1) {
        return new SqlSessionTemplate(sqlSessionFactory1);
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "HikariDataSource1")
    public HikariDataSource dataSource() {
        DataSourceProperties properties = firstDataSourceProperties();
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }
}

