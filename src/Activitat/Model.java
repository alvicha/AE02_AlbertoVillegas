package Activitat;

import java.io.File;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Classe que conté tots els mètodes per a realitzar les operacions de
 * l’aplicació
 */
public class Model {

	private Connection con;
	private DefaultTableModel tableModel;

	/**
	 * Constructor de la classe Model
	 */
	Model() {
		tableModel = new DefaultTableModel();
	}

	/**
	 * Métode Getter per a obtenir la connexió a la base de dades
	 * 
	 * @return Retorna l'objecte Connection associat a la instància de la classe
	 *         actual.
	 */
	public Connection getConexionBD() {
		return con;
	}

	/**
	 * Métode Getter per a gestionar dades tabulars de una taula.
	 * 
	 * @return Retorna l'objecte DefaultTableModel associat a la instància de la
	 *         classe actual
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Mètode per a tenir una connexió tipus client en la base de dades sobre un
	 * fitxer XML.
	 * 
	 */
	public void connexioClientBD() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("client.xml"));
			Element raiz = document.getDocumentElement();
			NodeList nodeList = document.getElementsByTagName("dades");
			String url = "", usuari = "", contrasenya = "";
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("");
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nodeList.item(i);
					url = eElement.getElementsByTagName("url").item(0).getTextContent();
					usuari = eElement.getElementsByTagName("usuari").item(0).getTextContent();
					contrasenya = eElement.getElementsByTagName("contrasenya").item(0).getTextContent();
				}
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, usuari, contrasenya);
			Statement stmt = con.createStatement();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mètode per a tenir una connexió tipus administrador en la base de dades sobre
	 * un fitxer XML.
	 * 
	 */
	public void conexioAdminBD() {
		try {
			con.close();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("admin.xml"));
			Element raiz = document.getDocumentElement();
			NodeList nodeList = document.getElementsByTagName("dades");
			String url = "", usuari = "", contrasenya = "";
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("");
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nodeList.item(i);
					url = eElement.getElementsByTagName("url").item(0).getTextContent();
					usuari = eElement.getElementsByTagName("usuari").item(0).getTextContent();
					contrasenya = eElement.getElementsByTagName("contrasenya").item(0).getTextContent();
				}
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, usuari, contrasenya);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mètode per a comprovar si l'usuari és administrador per a gestionar els
	 * permisos de l'aplicació.
	 * 
	 * @param usu    Nom d´usuari
	 * @param contra Contrasenya de l´usuari
	 * @return Retorna true si és administrador i false en cas contrari.
	 */
	@SuppressWarnings("finally")
	public boolean esAdministrador(String usu, String contra) {
		boolean resultado = false;
		try {
			String tipus = "";
			Statement stmt = con.createStatement();
			String consulta = "SELECT type FROM users WHERE user=? AND pass=?";
			PreparedStatement pstmt = con.prepareStatement(consulta);
			pstmt.setString(1, usu);
			pstmt.setString(2, contra);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				tipus = rs.getString("type");
			}

			if (tipus.equals("admin")) {
				resultado = true;
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return resultado;
		}
	}

	/**
	 * Métode Mètode per a comprovar si l'usuari existeix en la base de dades.
	 * 
	 * @param usu    Nom d´usuari
	 * @param contra Contrasenya de l´usuari
	 * @return Retorna true si existeix l'usuari i false en cas contrari.
	 */
	public boolean comprobarUsuari(String usu, String contra) {
		try {
			Statement stmt = con.createStatement();
			String consulta = "SELECT * FROM users WHERE user=? AND pass=?";
			PreparedStatement pstmt = con.prepareStatement(consulta);
			pstmt.setString(1, usu);
			pstmt.setString(2, contra);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Mètode per a executar consultes SQL de tipus SELECT, INSERT, UPDATE i DELETE.
	 * 
	 * @param consult Consulta SQL introduïda per l'usuari.
	 */
	public void executarConsulta(String consult) {
		tableModel.setColumnCount(0);
		tableModel.setRowCount(0);
		try {
			Statement stmt = con.createStatement();
			PreparedStatement pstmt = con.prepareStatement(consult);
			boolean esSelect = consult.trim().toUpperCase().startsWith("SELECT");
			boolean esInsert = consult.trim().toUpperCase().startsWith("INSERT");
			boolean esUpdate = consult.trim().toUpperCase().startsWith("UPDATE");
			boolean esDelete = consult.trim().toUpperCase().startsWith("DELETE");

			if (esSelect) {
				ResultSet rs = pstmt.executeQuery();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					tableModel.addColumn(rs.getMetaData().getColumnName(i));
				}

				while (rs.next()) {
					Object[] fila = new Object[rs.getMetaData().getColumnCount()];
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						fila[i - 1] = rs.getString(i);
					}
					tableModel.addRow(fila);
				}
			} else if (esInsert || esUpdate || esDelete) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Desitges segur fer l´execució?",
						"Confirmació de Execució", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					try {
						int filasAfectadas = pstmt.executeUpdate();
						if (filasAfectadas > 0) {
							JOptionPane.showMessageDialog(null, "S´ha realitzat correctament la consulta",
									"ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null,
									"L´operació no s´ha fet correctament, no s´ha produït canvis.", "ADVERTENCIA",
									JOptionPane.WARNING_MESSAGE);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "ERROR: " + ex.getLocalizedMessage(), "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Consulta introduïda no vàlida.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Mètode que pren una contrasenya com a Hash MD5 com a paràmetre per a
	 * convertir-ho en una cadena de caràcteres.
	 * 
	 * @param Contrasenya de l´usuari
	 * @return Retornarà la contrasenya que hi ha com a HASH MD5 en una cadena de
	 *         caràcters.
	 */

	public String convertidorPassAHash(String contra) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(contra.getBytes());
			byte[] digest = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Mètode per a tancar la connexió de la base de dades.
	 */

	public void tancarConnexio() {
		try {
			con.close();
			System.exit(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mètode per a tancar la sessió de l'usuari de l'aplicació sense tancar la
	 * connexió.
	 * 
	 * @param usuari      Nom d´usuari
	 * @param contrasenya Contrasenya de l´usuari
	 */

	public void tancarSessio(String usuari, String contrasenya) {
		if (esAdministrador(usuari, contrasenya)) {
			try {
				con.close();
				getTableModel().setColumnCount(0);
				getTableModel().setRowCount(0);
				connexioClientBD();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		getTableModel().setColumnCount(0);
		getTableModel().setRowCount(0);
	}
}