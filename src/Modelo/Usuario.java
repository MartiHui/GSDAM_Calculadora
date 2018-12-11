package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import utils.Connector;

public class Usuario {
	public int id_usuario;
	public String nombre;
	public String password;
	
	private Usuario(int id_usuario, String nombre, String password) {
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.password = password;
	}
	
	public static Usuario load(int id) {
		Connector.connect();
		PreparedStatement pstm;
		ResultSet rs;
		
		String query = "SELECT * FROM usuarios WHERE id_usuario = ?";
		
		try {
			pstm = Connector.con.prepareStatement(query);
			pstm.setInt(1, id);
			
			rs = pstm.executeQuery();
			
			if (rs.next()) {
				return new Usuario(rs.getInt("id_usuario"),
						rs.getString("nombre"),
						rs.getString("password"));
			}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return null;
	}
	
	public static Usuario insert(String nombre, String password) {
		Connector.connect();
		PreparedStatement pstm;
		ResultSet rs;
		
		String query = "INSERT INTO usuarios(nombre, password) VALUES(?, ?)";
		
		try {
			pstm = Connector.con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setString(1, nombre);
			pstm.setString(2, password);
			
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			
			if (rs.next()) {
				return Usuario.load(rs.getInt(1));
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return null;
	}
	
	public static Usuario login(String nombre, String password) {
		Connector.connect();
		PreparedStatement pstm;
		ResultSet rs;
		
		String query = "SELECT id_usuario FROM usuarios WHERE nombre = ? AND password = ?";
		
		try {
			pstm = Connector.con.prepareStatement(query);
			pstm.setString(1, nombre);
			pstm.setString(2, password);
			
			rs = pstm.executeQuery();
			
			if (rs.next()) {
				return Usuario.load(rs.getInt(1));
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectas.");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return null;
	}
}
