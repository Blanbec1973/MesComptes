package control;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Modele;
import vue.Vue;

public class Controle {
	private Parametres parametres;
	private Modele modele;
	private Vue vue;
	private static final Logger logger = LogManager.getLogger(Controle.class);
	
//	static {
//		System.setProperty("java.util.logging.SimpleFormatter.format", 
//	            "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
//		System.setProperty("java.util.logging.FileHandler.formatter", 
//	            "java.util.logging.SimpleFormatter.format");
//		Handler fh;
//		try {
//			fh = new FileHandler("%h/MesComptes%u.log",1024,3,true);
//			SimpleFormatter formatter = new SimpleFormatter();  
//			fh.setFormatter(formatter); 
//			LOGGER.addHandler(fh);
//		} catch (SecurityException | IOException e) {
//			LOGGER.severe("Impossible d'associer le FileHandler : "+e.getMessage());
//		}
//	}
	
	public Controle() {
		
		logger.info("Démarrage MesComptes");
		
		parametres = new Parametres("config.properties");
		
		modele = new Modele(parametres.getNomFichierBase());
		
		vue = new Vue(this);
		
		
		
	}

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel( new NimbusLookAndFeel());
		new Controle();

	}

	public void demandeClosing() {
		modele.close();
		
	}

	public void demandeInsertionLigneDeCompte(String saisieDate, String saisieLibelle,
			                                  String saisieMode, String saisieMontant,
			                                  boolean saisieFlagPec) {
		System.out.println(saisieDate+"-"+saisieLibelle+"-"+saisieMode+"-"+saisieMontant+"-"+saisieFlagPec);
		
	}
	
	public String demandeSolde(String numSolde) {
		return String.valueOf(modele.calculSolde(numSolde));
	}

}
