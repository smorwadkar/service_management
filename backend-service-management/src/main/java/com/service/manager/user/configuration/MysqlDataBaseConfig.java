package com.service.manager.user.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = 
	{"com.service.manager.user.persistence.mapper"}
	, sqlSessionFactoryRef = "mysqlSessionFactory" )
@EnableTransactionManagement
public class MysqlDataBaseConfig {
	@Value(value = "root")
	private String userName;

	@Value(value = "password")
	private String password;

	@Value(value = "localhost")
	private String host;

	@Value(value = "3306")
	private String port;

	@Value(value = "servicemanagement")
	private String schema;

	@Value(value = "com.mysql.jdbc.Driver")
	private String dbDriver;

	@Value(value = "${db.test.on.borrow:true}")
	private String testOnBorrow;

	@Value(value = "${db.validation.query:SELECT 1}")
	private String validationQuery;

	@Value(value = "${db.max.active:200}")
	private int maxActive;

	@Value(value = "${db.min.idle:0}")
	private int minIdle;
	
	@Value(value = "${db.max.idle:-1}")
	private int maxIdle;

	@Value(value = "${db.max.wait:10000}")
	private int maxWait;

	@Value(value = "${db.initial.size:10}")
	private int initialSize;

	@Bean(name = "mysqlSessionFactory")
	public SqlSessionFactoryBean mysqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mysqlDatasource());
		return sqlSessionFactoryBean;
	}

	@Bean(name = "mysqlTransactionManager")
	public DataSourceTransactionManager getMysqlTransactionManager()
			throws Exception {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(mysqlDatasource());
		return dataSourceTransactionManager;
	}

	@Bean(name = "mysqlDatasource")
	@Primary
	public DataSource mysqlDatasource() {
		DataSource datasource = new DataSource();
		datasource.setPoolProperties(configurePoolProperties());
		return datasource;
	}

	private PoolProperties configurePoolProperties() {
		PoolProperties poolProps = new PoolProperties();
		poolProps.setUrl(buildDataSourceURL());
		poolProps.setDriverClassName(dbDriver);
		poolProps.setUsername(userName);
		poolProps.setPassword(password);
		poolProps.setJmxEnabled(true);
		poolProps.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
		poolProps.setValidationQuery(validationQuery);
		poolProps.setMaxActive(maxActive);
		poolProps.setInitialSize(initialSize);
		poolProps.setMaxWait(maxWait);
		poolProps.setMinIdle(minIdle);
		poolProps.setMaxIdle(maxIdle);
		poolProps
				.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
						+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		return poolProps;
	}

	private String buildDataSourceURL() {
		StringBuffer temp = new StringBuffer("jdbc:mysql://");
		temp.append(host);
		temp.append(":").append(port);
		temp.append("/").append(schema);

		return temp.toString();
	}
}
