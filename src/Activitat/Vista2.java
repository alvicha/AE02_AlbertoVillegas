package Activitat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Vista2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txaConsultaSQL;
	private JButton btnExecutarConsulta;
	private JLabel lblConsulta;
	private JButton btnTancarConnexio;
	private JButton btnTancarSessio;
	private JTable taulaRegistresSQL;
	private JScrollPane scpTaulaRegistres;
	private JLabel lblTitol;
	private JPanel pnlConsultesSQL;
	private JScrollPane scpConsultaSQL;

	/**
	 * Launch the application.
	 */

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Vista2 frame = new Vista2();
	 * frame.setVisible(false); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public Vista2() {
		setTitle("Gestio de consultes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 539);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 255, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnExecutarConsulta = new JButton("EXECUTAR CONSULTA");
		btnExecutarConsulta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExecutarConsulta.setBounds(114, 436, 167, 31);
		contentPane.add(btnExecutarConsulta);

		btnTancarSessio = new JButton("TANCAR SESSIÓ");
		btnTancarSessio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTancarSessio.setBounds(766, 436, 153, 31);
		contentPane.add(btnTancarSessio);

		btnTancarConnexio = new JButton("TANCAR CONNEXIÓ");
		btnTancarConnexio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTancarConnexio.setBounds(541, 436, 153, 31);
		contentPane.add(btnTancarConnexio);

		scpTaulaRegistres = new JScrollPane();
		scpTaulaRegistres.setBounds(427, 90, 550, 305);
		contentPane.add(scpTaulaRegistres);

		taulaRegistresSQL = new JTable();
		scpTaulaRegistres.setViewportView(taulaRegistresSQL);

		lblTitol = new JLabel("FUNCIONALITATS CONSULTES SQL");
		lblTitol.setForeground(new Color(64, 0, 0));
		lblTitol.setBackground(new Color(134, 17, 111));
		lblTitol.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitol.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitol.setBounds(459, 26, 321, 31);
		contentPane.add(lblTitol);

		pnlConsultesSQL = new JPanel();
		pnlConsultesSQL.setBounds(10, 68, 396, 357);
		contentPane.add(pnlConsultesSQL);
		pnlConsultesSQL.setBackground(new Color(213, 213, 255));
		pnlConsultesSQL.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"CONSULTES SQL", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlConsultesSQL.setLayout(null);

		lblConsulta = new JLabel("CONSULTA SQL:");
		lblConsulta.setBounds(124, 37, 142, 19);
		pnlConsultesSQL.add(lblConsulta);
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulta.setFont(new Font("Tahoma", Font.BOLD, 15));

		scpConsultaSQL = new JScrollPane();
		scpConsultaSQL.setBounds(10, 71, 365, 257);
		pnlConsultesSQL.add(scpConsultaSQL);

		txaConsultaSQL = new JTextArea();
		scpConsultaSQL.setViewportView(txaConsultaSQL);
		setVisible(false);
	}

	JTable getTaulaRegistresSQL() {
		return taulaRegistresSQL;
	}

	JButton getBtnExecutarConsulta() {
		return btnExecutarConsulta;
	}

	JButton getBtnTancarConnexio() {
		return btnTancarConnexio;
	}

	JButton getBtnTancarSessio() {
		return btnTancarSessio;
	}

	JLabel getLblConsulta() {
		return lblConsulta;
	}

	JTextArea getTxaConsultaSQL() {
		return txaConsultaSQL;
	}

	JScrollPane getScpTaulaRegistres() {
		return scpTaulaRegistres;
	}

	JLabel getLblTitol() {
		return lblTitol;
	}

	JPanel getPnlConsultesSQL() {
		return pnlConsultesSQL;
	}

	JScrollPane getScpConsultaSQL() {
		return scpConsultaSQL;
	}
}
