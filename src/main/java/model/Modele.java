package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Modele {
	private SGBDManager sgbd;
	private Map <String,String> lstCodeNature = new HashMap<>();
	private Solde solde;
	private static final Logger logger = LogManager.getFormatterLogger(Modele.class);
	
	public Solde getSolde() {return solde;}
	public Map<String, String> getLstCodeNature() {return lstCodeNature;}

	public Modele(String nomFichierBase) {
		sgbd = new SGBDManager(nomFichierBase);
		sgbd.connect();
		
		solde= new Solde(sgbd);
		this.chargeTabCodeLibelle(lstCodeNature, "Code nature");	
		
	}
	
	public static void main(String[] args) {
		Modele m = new Modele("//C:\\\\Users/heynerr\\\\Documents\\\\0-Personnel\\\\Comptes\\\\Comptes.accdb");
		logger.info("Solde 0 : "+ m.getSolde().calculSoldePEC());
		m.close();
	}
	
	public void close() {
		sgbd.disconnect();
	}
	
	public void chargeTabCodeLibelle(Map<String, String> lstCodeNature, String nomTable) {
		String sql = "Select * from ["+nomTable+"] order by 1;";
		ResultSet rst = sgbd.selectSQL(sql);
		try {
			while (rst.next()) {
				lstCodeNature.put(rst.getString(1), rst.getString(2));
			}
			rst.close();
		} catch (SQLException e) {logger.error(e.getMessage());}
	}
	
	public boolean insereLigneCompte(String saisieDate, String saisieLibelle, String saisieMode, String saisieMontant,
			boolean saisieFlagPec) {
		return true;
	}
}
