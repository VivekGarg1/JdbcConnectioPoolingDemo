package com.home.util;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtilWithHikari {
	
	private static final String DB_DRIVER_CLASS="driver.class.name";
	private static final String DB_USERNAME="db.username";
	private static final String DB_PASSWORD="db.password";
	private static final String DB_URL="db.url";
	private static HikariDataSource dataSource=null;
	private static Properties properties=new Properties();
	
	static {
		try {
			properties.load(new FileInputStream("src/database.properties"));
			dataSource=new HikariDataSource();
			dataSource.setDriverClassName(properties.getProperty(DB_DRIVER_CLASS));
			dataSource.setJdbcUrl(properties.getProperty(DB_URL));
			dataSource.setUsername(properties.getProperty(DB_USERNAME));
			dataSource.setPassword(properties.getProperty(DB_PASSWORD));
			
			dataSource.setMinimumIdle(100);
			dataSource.setMaximumPoolSize(1000);
			System.out.println("DataSource: "+dataSource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getDataSource() throws SQLException {
		return dataSource.getConnection();
	}
}
