package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import database.TableSchema.Column;

/**
 * modella l’insieme di transazioni collezionate in una tabella. La singola
 * transazione è modellata dalla classe Example.
 * 
 * @author alexc
 *
 */
public class TableData {

	DbAccess db;

	public TableData(DbAccess db) {
		this.db = db;
	}

	/**
	 * 
	 * Comportamento: : Ricava lo schema della tabella con nome table. Esegue una
	 * interrogazione per estrarre le tuple distinte da tale tabella. Per ogni tupla
	 * del resultset, si crea un oggetto, 4 istanza della classe Example, il cui
	 * riferimento va incluso nella lista da restituire. In particolare, per la
	 * tupla corrente nel resultset, si estraggono i valori dei singoli campi
	 * (usando getFloat() o getString()), e li si aggiungono all’oggetto istanza
	 * della classe Example che si sta costruendo. Il metodo può propagare un
	 * eccezione di tipo SQLException (in presenza di errori nella esecuzione della
	 * query) o EmptySetException (se il resultset è vuoto)
	 * 
	 * @param table
	 * @return
	 * @throws SQLException
	 * @throws EmptySetException
	 */
	public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptyTypeException {
		Example ex = new Example();
		List<Example> list = new ArrayList<Example>();
		Statement s = this.db.getConnection().createStatement();
		ResultSet r = s.executeQuery("SELECT * FROM "+table);

		while (r.next()) {
			ex.add(r.getString(1));
			ex.add(r.getFloat(2));
			ex.add(r.getString(3));
			ex.add(r.getString(4));
			ex.add(r.getString(5));

			list.add(ex);

		}
		return list;
	}

	/**
	 * 
	 * Comportamento: Formula ed esegue una interrogazione SQL per estrarre i valori
	 * distinti ordinati di column e popolare un insieme da restituire (scegliere
	 * opportunamente in Set da utilizzare).
	 * 
	 * @param table
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public Set<Object> getDistinctColumnValues(String table, Column column) throws SQLException {
		Object obj = null;
		Set<Object> objects = new TreeSet<Object>();
		Statement s = this.db.getConnection().createStatement();
		ResultSet r = s.executeQuery("SELECT" + column + "FROM" + table + "order by" + column + "asc");
		while (r.next()) {
			obj = r.getObject(1);
			objects.add(obj);

		}
		return objects;
	}

	/**
	 * 
	 * Formula ed esegue una interrogazione SQL per estrarre il valore aggregato
	 * (valore minimo o valore massimo) cercato nella colonna di nome column della
	 * tabella di nome table. Il metodo solleva e propaga una NoValueException se il
	 * resultset è vuoto o il valore calcolato è pari a null N.B. aggregate è di
	 * tipo QUERY_TYPE dove QUERY_TYPE è la classe enumerativa fornita dal docente
	 * 
	 * @param table
	 * @param column
	 * @param aggregate
	 * @return
	 * @throws SQLException
	 * @throws NoValueException
	 */
	public Object getAggregateColumnValue(String table, Column column, QUERY_TYPE aggregate)
			throws SQLException, NoValueException {
		Object obj = null;
		Statement s = this.db.getConnection().createStatement();
		ResultSet r = s.executeQuery("SELECT " + aggregate + "(" + column + ") from" + table);
		obj = r.getObject(1);
		return obj;
	}

}
