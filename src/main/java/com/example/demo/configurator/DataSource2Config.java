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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
public class DataSource2Config {

    @Bean(name = "dataSource2")
    //@Primary
    @ConfigurationProperties(prefix = "spring.second.datasource")
    public DataSource dataSource2() {
        System.out.println("created bean dataSource2 !!!");
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("postgres")
                .password("LoA12Kie17")
                .build();
    }


    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2")final DataSource dataSource2) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource2);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis-mapper-xml/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2")final SqlSessionFactory sqlSessionFactory2) {
        return new SqlSessionTemplate(sqlSessionFactory2);
    }

    @Bean
    @ConfigurationProperties("spring.second.datasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "HikariDataSource2")
    @Primary
    public HikariDataSource dataSource() {
        DataSourceProperties properties = secondDataSourceProperties();
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

}