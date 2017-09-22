package io.renren.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
public class DBConfiguration {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(DBConfiguration.class);

	@Bean(name = "dataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource DataSource() {
		logger.info("-------------------- DB DataSource init ---------------------");
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "sqlSessionFactory")
	@Primary
	public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		logger.info("-------------------- DB session factory  init ---------------------");
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// bean.setTypeAliasesPackage(PACKAGE);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setUseGeneratedKeys(true);// 使用jdbc的getGeneratedKeys获取数据库自增主键值
		configuration.setUseColumnLabel(true);// 使用列别名替换列名 select user as User
		configuration.setMapUnderscoreToCamelCase(true);// -自动使用驼峰命名属性映射字段
		bean.setConfiguration(configuration);
		bean.setFailFast(true);

		return bean.getObject();
	}

	@Bean(name = "transactionManager")
	@Primary
	public DataSourceTransactionManager TransactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
