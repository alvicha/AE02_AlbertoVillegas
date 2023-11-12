package Activitat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuari;
	private JButton btnIniciarSessio;
	private JLabel lblUsuari;
	private JLabel lblContrasenya;
	private JLabel lblIniciSessio;
	private JPanel pnllniciarSessio;
	private JPasswordField pfContrasenya;

	/**
	 * Launch the application.
	 */

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Vista frame = new Vista();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public Vista() {
		setTitle("Inici de Sessió");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(198, 255, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIniciSessio = new JLabel("INICI DE SESSIÓ");
		lblIniciSessio.setForeground(new Color(255, 0, 0));
		lblIniciSessio.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblIniciSessio.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciSessio.setBounds(112, 30, 213, 47);
		contentPane.add(lblIniciSessio);

		pnllniciarSessio = new JPanel();
		pnllniciarSessio.setBackground(new Color(252, 255, 213));
		pnllniciarSessio.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnllniciarSessio.setBounds(58, 98, 314, 255);
		contentPane.add(pnllniciarSessio);
		pnllniciarSessio.setLayout(null);

		lblUsuari = new JLabel("NOM DE USUARI");
		lblUsuari.setBounds(85, 22, 143, 20);
		pnllniciarSessio.add(lblUsuari);
		lblUsuari.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuari.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnIniciarSessio = new JButton("INICIAR SESSIÓ");
		btnIniciarSessio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIniciarSessio.setBounds(80, 180, 158, 31);
		pnllniciarSessio.add(btnIniciarSessio);

		lblContrasenya = new JLabel("CONTRASENYA");
		lblContrasenya.setBounds(85, 104, 143, 20);
		pnllniciarSessio.add(lblContrasenya);
		lblContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenya.setFont(new Font("Tahoma", Font.BOLD, 15));

		txtUsuari = new JTextField();
		txtUsuari.setBounds(25, 63, 257, 20);
		pnllniciarSessio.add(txtUsuari);
		txtUsuari.setColumns(10);

		pfContrasenya = new JPasswordField();
		pfContrasenya.setBounds(25, 136, 257, 20);
		pnllniciarSessio.add(pfContrasenya);
		setVisible(true);
	}

	JButton getBtnIniciarSessio() {
		return btnIniciarSessio;
	}

	JTextField getTxtUsuari() {
		return txtUsuari;
	}

	JTextField getPfContrasenya() {
		return pfContrasenya;
	}

	JLabel getLblUsuari() {
		return lblUsuari;
	}

	JLabel getLblContrasenya() {
		return lblContrasenya;
	}

	JLabel getLblIniciSessio() {
		return lblIniciSessio;
	}

	JPanel getPnllniciarSessio() {
		return pnllniciarSessio;
	}
}
