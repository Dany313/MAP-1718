package database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DbAccess {
	/**
	 * driver accesso a mysql
	 */
	String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
	final String DBMS = "jdbc:mysql";
	/**
	 * contiene l’identificativo del server su cui risiede la base di 3 dati (per
	 * esempio localhost)
	 */
	final String SERVER = "localhost";
	/**
	 * contiene il nome della base di dati
	 */
	final String DATABASE = "MapDB";
	/**
	 * La porta su cui il DBMS MySQL accetta le connessioni
	 */
	final String PORT = "3306";
	/**
	 * contiene il nome dell’utente per l’accesso alla base di dati
	 */
	final String USER_ID = "MapUser";
	/**
	 * contiene la password di autenticazione per l’utente identificato da USER_ID
	 */
	final String PASSWORD = "map";
	/**
	 * gestisce una connessione
	 */
	Connection conn;

	/**
	 * impartisce al class loader l’ordine di caricare il driver mysql, inizializza
	 * la connessione riferita da conn. Il metodo solleva e propaga una eccezione di
	 * tipo DatabaseConnectionException in caso di fallimento nella connessione al
	 * database
	 * 
	 * @throws DatabaseConnectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	void initConnection() throws DatabaseConnectionException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException {

		Class.forName(DRIVER_CLASS_NAME).newInstance();

		String connessione = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE;
		this.conn = (Connection) DriverManager.getConnection(connessione, this.USER_ID, this.PASSWORD);
		if (this.conn == null) {
			System.out.println("connessione nulla");
		}

	}

	/**
	 * restituisce conn;
	 * 
	 * @return
	 */
	Connection getConnection() {
		return this.conn;
	}

	/**
	 * chiude la connessione conn
	 */
	void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
