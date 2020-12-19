package control;

import java.util.logging.Logger;

import model.Modele;
import vue.Vue;

public class Controle {
	private Parametres parametres;
	private Modele modele;
	private Vue vue;
	private static final Logger logger = Logger.getLogger(Controle.class.getPackage().getName());
	
	public Controle() {
		logger.info("Démarrage MesComptes");
		parametres = new Parametres("config.properties");
		
		modele = new Modele(parametres.getNomFichierBase());
		
		vue = new Vue(this);
		
	}

	public static void main(String[] args) {
		new Controle();

	}

	public void demandeClosing() {
		modele.close();
		
	}

}
