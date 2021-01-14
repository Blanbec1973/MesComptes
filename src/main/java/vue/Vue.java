package vue;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import control.Controle;


public class Vue {
	private JFrame fen;
	private Controle controle;
	private JTextField saisieDate = new JTextField();
	private JTextField saisieLibelle = new JTextField();
	JComboBox<Object> cmbMode = new JComboBox<>();
	private JTextField saisieMontant = new JTextField();
	private JCheckBox saisieFlagPec = new JCheckBox();
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		new Vue(null);
	}
	
	public Vue(Controle controle) throws UnsupportedLookAndFeelException
		{
		UIManager.setLookAndFeel( new NimbusLookAndFeel() );
		this.controle=controle;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension dimEcran = tk.getScreenSize();
		int largEcran=dimEcran.width;
		int hautEcran=dimEcran.height;
		int largFenetre = largEcran * 4 / 5;
		int hautFenetre = hautEcran * 4 / 5 ;
		fen = new JFrame("Mes comptes");
    	fen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fen.setSize(largFenetre, hautFenetre);
		fen.setLocationRelativeTo(null);
		fen.setResizable(false);
		JPanel pnlMain = (JPanel) fen.getContentPane();
		pnlMain.setLayout(new BorderLayout());
		//setBounds(50,100,largFenetre,hautFenetre); // coordonnées coin supérieur gauche + largeur + hauteur
		
		pnlMain.add(buildPnlSolde(), BorderLayout.NORTH);
		
		pnlMain.add(buildPnlSaisie(), BorderLayout.SOUTH);
		
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	if (controle != null) controle.demandeClosing();
            }
        };
        fen.addWindowListener(exitListener);
		fen.setVisible(true);
	}
	private JPanel 	buildPnlSolde() {
		JPanel pnlSoldes = new JPanel();
		pnlSoldes.setLayout(new GridLayout(1,4));
		
		Date maDate = new Date();
		String tempString = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE).format(maDate);
		JLabel lblMaDate = new JLabel("Date : "+tempString);
		pnlSoldes.add(lblMaDate);
		
		pnlSoldes.add(new JLabel("Solde PEC Banque : "+controle.demandeSoldePEC()));
		pnlSoldes.add(new JLabel("Mvts non PEC avant M+1 : "+controle.demandeSommeMvtNonPecAvantMoisSuivant()));
		pnlSoldes.add(new JLabel("Solde à fin de mois : "+controle.demandeSoldeAvantMoisSuivant()));
		
		return pnlSoldes;
	}
	
	private JPanel buildPnlSaisie() {
		JPanel pnlSaisie = new JPanel();
		pnlSaisie.setLayout(new GridLayout(2,6));
		
		JLabel lblDate = new JLabel("Date");
		pnlSaisie.add(lblDate);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblLibelle = new JLabel("Libellé");
		pnlSaisie.add(lblLibelle);
		lblLibelle.setHorizontalAlignment(SwingConstants.CENTER);	
		JLabel lblMode = new JLabel("Mode");
		pnlSaisie.add(lblMode);
		lblMode.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblMontant = new JLabel("Montant");
		pnlSaisie.add(lblMontant);
		lblMontant.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblFlagPec = new JLabel("Flag PEC");
		pnlSaisie.add(lblFlagPec);
		lblFlagPec.setHorizontalAlignment(SwingConstants.CENTER);
		pnlSaisie.add(new JLabel(""));
		
		for (Map.Entry mapentry : controle.demandeListeNature().entrySet()) {
			cmbMode.addItem(mapentry.getKey());
		}
		
		pnlSaisie.add(saisieDate);
		pnlSaisie.add(saisieLibelle);		
		pnlSaisie.add(cmbMode);
		cmbMode.setSelectedItem(null);
		pnlSaisie.add(saisieMontant);
		pnlSaisie.add(saisieFlagPec);
		
		JButton enregistrerBouton = new JButton("Enregistrer");
		pnlSaisie.add(enregistrerBouton);
        enregistrerBouton.addActionListener(event -> controle.demandeInsertionLigneDeCompte(saisieDate.getText(),
                		                                                                    saisieLibelle.getText(),
                		                                                                    cmbMode.getSelectedItem().toString(),
                		                                                                    saisieMontant.getText(),
                		                                                                    saisieFlagPec.isSelected()));
  		return pnlSaisie;
	}

	public void razZonesSaisie() {
		saisieDate.setText("");
		saisieLibelle.setText("");
		cmbMode.setSelectedItem(null);
		saisieMontant.setText("");
		saisieFlagPec.setSelected(false);
		saisieDate.requestFocus();
		
	}

	public void afficheMessageErreur() {
		// TODO Auto-generated method stub
		
	}

	
}



