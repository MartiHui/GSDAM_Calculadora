package utils;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Connector {
	private static String dbName = "calculadora_db";
	private static String loginUser = "root";
	private static String loginPassword = "";
	private static String hostAddress = "localhost";
	// https://stackoverflow.com/questions/26515700/mysql-jdbc-driver-5-1-33-time-zone-issue
	private static String timezone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true"
			+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String url = 
			String.format("jdbc:mysql://%s/%s%s", hostAddress, dbName, timezone);
	
	public static Connection con = null;
	
	public static void connect() {
		try {
			if ((con == null) || (con.isClosed())) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, loginUser, loginPassword);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public static void disconnect() {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
