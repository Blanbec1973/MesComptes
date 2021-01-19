package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.table.DefaultTableModel;

public class Utils {
	
	private Utils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String formatMontant(float nombre) {
		StringBuilder str = new StringBuilder();
		if (nombre < 0) {
			str.append("- ");
		}
		else {
			if (nombre > 0) str.append("+ ");
		}
		
		DecimalFormat df = new DecimalFormat("###,###,##0.00 €");
		str.append(df.format(nombre));
		
		return str.toString();
	}
	
	public static String premierJourMoisSuivant() {
		LocalDate dateJour = LocalDate.now();
		int mois = dateJour.getMonthValue();
		int annee = dateJour.getYear();
		
		if (mois == 12) {
			mois = 1;
			annee+=1;
		}
		else mois+=1;
		
		LocalDate datePemierMoisSuivant = LocalDate.of(annee,mois,1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return formatter.format(datePemierMoisSuivant);
	}
	
	public static String [] recupereEnTetes(ResultSet rst) throws SQLException {
		int nombreColonnes = rst.getMetaData().getColumnCount();
		String [] entetes = new String[nombreColonnes];
		
		for (int i = 0; i < nombreColonnes; i++) {
			entetes[i]=rst.getMetaData().getColumnName(i+1);
		}
		return entetes;
	}
	
	public static void recupereData(ResultSet rst, DefaultTableModel tableModele) throws SQLException {
		int nombreColonnes = rst.getMetaData().getColumnCount();
		while(rst.next()){
			 Object[] objects = new Object[nombreColonnes];
			 for(int i=0;i<nombreColonnes;i++){
			  objects[i]=rst.getObject(i+1);
			  }
			 tableModele.addRow(objects);
			}
	}
}
