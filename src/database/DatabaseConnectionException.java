package database;

import java.sql.SQLException;

/**
 *  modella il fallimento nella connessione al database. 
 
 * 
 *
 */


public class DatabaseConnectionException extends SQLException{
   public DatabaseConnectionException(){
	   System.err.println("connessione al database fallita");
   }
}
