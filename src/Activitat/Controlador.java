package Activitat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Classe que gestiona les accions que es produeixen segons els botons que es
 * polsen.
 * 
 */
public class Controlador {

	private Model model;
	private Vista vista;
	private Vista2 vista2;
	private ActionListener actionListenerBtnIniciarSessio;
	private ActionListener actionListenerBtnExecutarConsulta;
	private ActionListener actionListenerBtnTancarSessio;
	private ActionListener actionListenerBtnTancarConnexio;

	/**
	 * Constructor que se li passa com a paràmetre una instància de la Vista, altre
	 * de Vista2 i una instància del Model.
	 * 
	 * @param vist  Primera ventana de l´aplicació
	 * @param vist2 Segona ventana de l´aplicació
	 * @param mod   Model
	 */
	Controlador(Vista vist, Vista2 vist2, Model mod) {
		this.vista = vist;
		this.vista2 = vist2;
		this.model = mod;
		control();
	}

	/**
	 * Mètode per a controlar la interacció entre vista, vista2 i model.
	 */
	public void control() {
		model.connexioClientBD();
		vista2.setVisible(false);
		actionListenerBtnIniciarSessio = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuari = vista.getTxtUsuari().getText();
				String contrasenya = vista.getPfContrasenya().getText();
				if (usuari.isEmpty() || contrasenya.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Has d´introduïr el nom d´usuari o contrasenya", "ADVERTÈNCIA",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					contrasenya = model.convertidorPassAHash(contrasenya);
					if (model.comprobarUsuari(usuari, contrasenya)) {
						if (model.esAdministrador(usuari, contrasenya)) {
							model.conexioAdminBD();
							vista2.setVisible(true);
							vista.dispose();
							JOptionPane.showMessageDialog(null, "T'has connectat com a usuari administrador.",
									"INFORMACIÓ", JOptionPane.INFORMATION_MESSAGE);
						} else {
							model.connexioClientBD();
							vista2.setVisible(true);
							vista.dispose();
							JOptionPane.showMessageDialog(null, "T'has connectat com a usuari client.", "INFORMACIÓ",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"L´usuari o la contrasenya no existeix en la base de dades.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		};
		vista.getBtnIniciarSessio().addActionListener(actionListenerBtnIniciarSessio);

		actionListenerBtnExecutarConsulta = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String consulta = vista2.getTxaConsultaSQL().getText();
				model.executarConsulta(consulta);
				vista2.getTaulaRegistresSQL().setModel(model.getTableModel());
			}
		};
		vista2.getBtnExecutarConsulta().addActionListener(actionListenerBtnExecutarConsulta);

		actionListenerBtnTancarConnexio = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null,
						"Estàs segur que vols tancar la connexió d´aquest usuari?", "Tancar Connexió Usuari",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					model.tancarConnexio();
				}
			}
		};
		vista2.getBtnTancarConnexio().addActionListener(actionListenerBtnTancarConnexio);

		actionListenerBtnTancarSessio = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Estàs segur que vols tancar sessió?",
						"Tancar Sessió Aplicació", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					String usuari = vista.getTxtUsuari().getText();
					String contrasenya = vista.getPfContrasenya().getText();
					model.tancarSessio(usuari, contrasenya);
					vista2.getTxaConsultaSQL().setText("");
					vista.getTxtUsuari().setText("");
					vista.getPfContrasenya().setText("");
					vista2.setVisible(false);
					vista.setVisible(true);
				}
			}
		};
		vista2.getBtnTancarSessio().addActionListener(actionListenerBtnTancarSessio);
	}
}
