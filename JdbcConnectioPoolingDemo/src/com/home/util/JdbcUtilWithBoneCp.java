package com.home.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class JdbcUtilWithBoneCp {
	
	private static final String DB_DRIVER_CLASS="driver.class.name";
	private static final String DB_USERNAME="db.username";
	private static final String DB_PASSWORD="db.password";
	private static final String DB_URL="db.url";
	private static BoneCP dataSource=null;
	private static Properties properties=new Properties();
	
	static {
		try {
			properties.load(new FileInputStream("src/database.properties"));
			BoneCPConfig boneCPConfig=new BoneCPConfig();
			boneCPConfig.setJdbcUrl(properties.getProperty(DB_URL));
			boneCPConfig.setUser(properties.getProperty(DB_USERNAME));
			boneCPConfig.setPassword(properties.getProperty(DB_PASSWORD));
			
			boneCPConfig.setMinConnectionsPerPartition(10);
			boneCPConfig.setPartitionCount(5);
			dataSource=new BoneCP(boneCPConfig);
			System.out.println("DataSource: "+dataSource);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getDataSource() throws SQLException {
		return dataSource.getConnection();
	}
}
