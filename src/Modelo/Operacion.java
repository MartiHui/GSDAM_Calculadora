package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import utils.Connector;

public class Operacion {
	public int id_operacion;
	public Usuario usuario;
	public String fecha;
	public String operacion;
	public String operacionFormateada;
	public double resultado;
	
	private Operacion(int id_operacion, Usuario usuario, String fecha,
			String operacion, String operacionFormateada, double resultado) {
		this.id_operacion = id_operacion;
		this.usuario = usuario;
		this.fecha = fecha;
		this.operacion = operacion;
		this.operacionFormateada = operacionFormateada;
		this.resultado = resultado;
	}
	
	public static void insert(Usuario usuario, String operacion, String operacionFormateada,
			double resultado) {
		Connector.connect();
		PreparedStatement pstm;
		ResultSet rs;
		
		String query = "INSERT INTO operaciones(id_usuario, fecha, operacion, operacion_formateada,"
				+ " resultado) VALUES(?, ?, ?, ?, ?)";
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		try {
			pstm = Connector.con.prepareStatement(query);
			pstm.setInt(1, usuario.id_usuario);
			pstm.setString(2, currentTime);
			pstm.setString(3, operacion);
			pstm.setString(4, operacionFormateada);
			pstm.setDouble(5, resultado);
			
			pstm.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	public void delete() {
		Connector.connect();
		PreparedStatement pstm;
		ResultSet rs;
		
		String query = "DELETE FROM operaciones WHERE id_operacion = ?";
		
		try {
			pstm = Connector.con.prepareStatement(query);
			pstm.setInt(1, this.id_operacion);
			
			pstm.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public static LinkedList<Operacion> buscarPorUsuario(Usuario usuario) {
		LinkedList<Operacion> operaciones = new LinkedList<>();
		
		Connector.connect();
		PreparedStatement pstm;
		ResultSet rs;
		
		String query = "SELECT * FROM operaciones WHERE id_usuario = ? ORDER BY fecha DESC";
		
		try {
			pstm = Connector.con.prepareStatement(query);
			pstm.setInt(1, usuario.id_usuario);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Operacion op = new Operacion(rs.getInt("id_operacion"),
						usuario,
						rs.getString("fecha"),
						rs.getString("operacion"),
						rs.getString("operacionFormateada"),
						rs.getDouble("resultado"));
				operaciones.add(op);
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return operaciones;
	}
}
