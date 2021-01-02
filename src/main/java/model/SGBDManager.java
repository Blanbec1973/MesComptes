package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SGBDManager {
    private String path;
    private Connection connection;
    private Statement statement;
    private ResultSet resultset;
	private static final Logger logger = LogManager.getLogger(SGBDManager.class);
	
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
        	logger.error("Impossible de se connecter à la base");
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
        	logger.error(e.getMessage());
            return false;
        }
    }

    public ResultSet selectSQL(String sql) {
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            return resultset;
        } catch (SQLException ex) {
        	logger.error(ex.getMessage());
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
        	logger.error(e.getMessage());
        }
    }


	
	
	
	
}