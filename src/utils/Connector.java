package utils;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Connector {
	private static String dbName = "calculadora_db";
	private static String loginUser;
	private static String loginPassword;
	private static String hostAddress = "localhost";
	private static String url = 
			String.format("jdbc:mysql://%s/%s", hostAddress, dbName);
	
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
