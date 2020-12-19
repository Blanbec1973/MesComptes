package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Logger;

public class SGBDManager {
    private String path;
    private Connection connection;
    private Statement statement;
    private ResultSet resultset;
    private static final Logger logger = Logger.getLogger(SGBDManager.class.getPackage().getName());
	
    public Connection getConnection() {return connection;}

    /* Constructeur */
    public SGBDManager(String path) {
        this.path = path;
        this.statement=null;
        this.resultset=null;
    }

    /*
    *Connection à la base
    *@return : true si la connexion est réussie, false si échouée
    */
    public boolean connect() {
        try {
            String connectionString = "jdbc:ucanaccess:" + path;
            connection = DriverManager.getConnection(connectionString);
        }
        catch (SQLException e) {
            logger.severe("Impossible de se connecter à la base");
            return false;
        } 
        logger.info("Connexion SGBD OK.");
        return true;
    }

    /*
    *Déconnexion de la base
    *@return : true si la déconnexion est réussie, false sinon
    */
    public boolean disconnect() {
        try {
            connection.close();
            logger.info("Déconnexion SGBD OK.");
            return true;
        }
        catch (SQLException e) {
        	logger.severe(e.getMessage());
            return false;
        }
    }

    public ResultSet slectSQL(String sql) {
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            return resultset;
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
            return null;
        }  
        
    }

    /*
    *Envoi d'une requête de mise à jour (insert, update, delete)
    *@param : sql
    */
    public void updateSql(String sql) throws SQLException {
        statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e) {
        	logger.severe(e.getMessage());
        }
    }

	public void chargeTabCodeLibelle(Map<String, String> lstCodeNature, String nomTable) {
		String sql = "Select * from ["+nomTable+"] order by 1;";
		ResultSet rst = this.slectSQL(sql);
		try {
			while (rst.next()) {
				lstCodeNature.put(rst.getString(1), rst.getString(2));
			}
			rst.close();
		} catch (SQLException e) {
        	logger.severe(e.getMessage());
		}
		
	}
}