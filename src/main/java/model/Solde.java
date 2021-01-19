package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import control.Parametres;
import control.Utils;

public class Solde {
	private SGBDManager sgbd;
	private Parametres parametres;
	private static final Logger logger = LogManager.getFormatterLogger(Modele.class);

	
	public Solde(SGBDManager sgbd, Parametres parametres) {
		this.sgbd=sgbd;
		this.parametres=parametres;
	}

	public float calculSoldePEC() {
		String sql = String.format(parametres.getProperty("SQL_SOLDEPEC"), "1");
		logger.info(sql);
		return calculSolde(sql);
		}
	
	public float calculCumulMvtsNonPecAvantMoisSuivant() {
		String sql = String.format(parametres.getProperty("SQL_MVTNONPEC"), Utils.premierJourMoisSuivant());
		logger.info(sql);
		return calculSolde(sql);
	}
	
	public float calculSoldeAvantMoisSuivant() {
		String sql = String.format(parametres.getProperty("SQL_SOLDEMOISSUIVANT"), Utils.premierJourMoisSuivant());
		logger.info(sql);
		return calculSolde(sql);
	}
	
	private float calculSolde(String sql) {
		float solde=0;
		ResultSet rst = sgbd.selectSQL(sql);
		try {
			rst.next();
			solde = rst.getFloat(1);
			rst.close();
		} catch (SQLException e) {logger.error(e.getMessage()); return 0;}
		return solde;
	}
	
}
