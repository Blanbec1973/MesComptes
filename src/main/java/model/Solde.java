package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import control.Utils;

public class Solde {
	private SGBDManager sgbd;
	private static final Logger logger = LogManager.getFormatterLogger(Modele.class);
	private static final String SQL_SOLDEPEC = "SELECT Sum([BPVF Compte chèque].[MontantMvt]) AS Somme, "
			                                 + "[BPVF Compte chèque].[PriseEnCompteMvt] AS pec "
			                                 + "FROM [BPVF Compte chèque] "
			                                 + "WHERE [BPVF Compte chèque].[PriseEnCompteMvt] = '%s' "
		                                     + "GROUP BY [BPVF Compte chèque].[PriseEnCompteMvt]";
	private static final String SQL_MVTNONPEC = "SELECT Sum([BPVF Compte chèque].[MontantMvt]) AS Somme "
								              + "FROM [BPVF Compte chèque] "
								              + "WHERE [BPVF Compte chèque].[PriseEnCompteMvt] Is null "
								              + "AND [BPVF Compte chèque].[DateMvt]<#%s# "
								              + "GROUP BY [BPVF Compte chèque].[PriseEnCompteMvt]";	
	private static final String SQL_SOLDEMOISSUIVANT = "SELECT Sum([BPVF Compte chèque].[MontantMvt]) AS Somme "
									                 + "FROM [BPVF Compte chèque] "
									                 + "WHERE [BPVF Compte chèque].[DateMvt]<#%s# ";	
	
	public Solde(SGBDManager sgbd) {
		this.sgbd=sgbd;
	}

	public float calculSoldePEC() {
		String sql = String.format(SQL_SOLDEPEC, "1");
		logger.info(sql);
		return calculSolde(sql);
		}
	
	public float calculCumulMvtsNonPecAvantMoisSuivant() {
		String sql = String.format(SQL_MVTNONPEC, Utils.premierJourMoisSuivant());
		logger.info(sql);
		return calculSolde(sql);
	}
	
	public float calculSoldeAvantMoisSuivant() {
		String sql = String.format(SQL_SOLDEMOISSUIVANT, Utils.premierJourMoisSuivant());
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
