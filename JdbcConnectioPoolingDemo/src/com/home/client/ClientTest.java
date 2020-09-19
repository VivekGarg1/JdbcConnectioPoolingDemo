package com.home.client;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.home.util.JdbcUtilWithApacheDBCP;
import com.home.util.JdbcUtilWithBoneCp;
import com.home.util.JdbcUtilWithC3P0;
import com.home.util.JdbcUtilWithHikari;

public class ClientTest {

	public static void main(String[] args) {
		try(/*Connection connection=JdbcUtilWithApacheDBCP.getDataSource().getConnection();*/
				/*Connection connection=JdbcUtilWithC3P0.getDataSource().getConnection();*/
				Connection connection=JdbcUtilWithBoneCp.getDataSource();
				/*Connection connection=JdbcUtilWithHikari.getDataSource();*/
				Statement statement=connection.createStatement()){
			String SQL="select * from employee_table";	
			ResultSet rs = statement.executeQuery(SQL);
			while(rs.next()) {
				int employee_id = rs.getInt("employee_id");
				String employee_name=rs.getString("employee_name");
				String email=rs.getString("email");
				Double salary=rs.getDouble("salary");
				Date joiningDate=rs.getDate("date_of_joining");
				BigDecimal bonus=rs.getBigDecimal("bonus");
				System.out.println("EmployeeId: "+employee_id);
				System.out.println("Employee name: "+employee_name);
				System.out.println("Email: "+email);
				System.out.println("Employee joining date: "+joiningDate);
				System.out.println("Bonus: "+bonus);
				System.out.println("--------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
