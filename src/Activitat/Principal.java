package Activitat;

/**
 * Classe per a iniciar l´aplicació
 */
public class Principal {

	public static void main(String[] args) {
		Vista vista = new Vista();
		Model model = new Model();
		Vista2 vista2 = new Vista2();
		Controlador controlador = new Controlador(vista, vista2, model);
	}
}
