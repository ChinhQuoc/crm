package config;

import java.sql.Connection;
import java.sql.DriverManager;

// config JDBC connect to sever mySql
public class MySqlConfig {
	
	// static: giá trị của func sẽ lưu trực tiếp trên RAM
	public static Connection getConnection() {
		try {
			// khai báo driver sd cho JDBC tương ứng với CSDL cần kết nối
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Khai báo Driver sẽ mở kết nối đến CSDL nào
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm", "root", "admin123");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi kết nối database " + e.getLocalizedMessage());
			return null;
		}
	}
}
