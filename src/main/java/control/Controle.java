package control;

import java.sql.ResultSet;
import java.util.Map;

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
	
	public Controle() throws UnsupportedLookAndFeelException {
		
		logger.info("Démarrage MesComptes");
		
		parametres = new Parametres("config.properties");
		
		modele = new Modele(this, parametres.getProperty("nomFichierBase"));
		
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
		if (modele.insereLigneCompte(saisieDate, saisieLibelle, saisieMode, saisieMontant, saisieFlagPec)) {
			vue.razZonesSaisie();
			vue.refreshTableMvtNonPec();
		}
		else
			vue.afficheMessageErreur();
		
	}
	
	public Parametres getParametres() {
		return parametres;
	}

	public String demandeSoldePEC() {
		return Utils.formatMontant(modele.getSolde().calculSoldePEC());
	}

	public String demandeSommeMvtNonPecAvantMoisSuivant() {
		return Utils.formatMontant(modele.getSolde().calculCumulMvtsNonPecAvantMoisSuivant());
	}
	
	public String demandeSoldeAvantMoisSuivant() {
		return Utils.formatMontant(modele.getSolde().calculSoldeAvantMoisSuivant());
	}

	public Map<String,String> demandeListeNature() {
		return modele.getLstCodeNature();
	}
	
	public ResultSet demandeMouvementsNonPEC() {
		return modele.getRstMouvementsNonPec();
	}
}
