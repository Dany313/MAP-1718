package database;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.mysql.jdbc.ResultSetMetaData;

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
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws EmptySetException
	 */
	public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptyTypeException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {

		// Example colonne = new Example();
		List<Example> list = new ArrayList<Example>();
		db.initConnection();
		this.db.getConnection();
		Statement s = this.db.getConnection().createStatement();

		ResultSet r = s.executeQuery("SELECT * FROM " + table);
		ResultSetMetaData colonne = (ResultSetMetaData) r.getMetaData();

		while (r.next()) {
			Example ex = new Example();
			for (int i = 1; i <= colonne.getColumnCount(); i++) {
				if (r.getObject(i).getClass().equals(String.class)) {
					ex.add(r.getString(i));
				} else {
					Double value = new BigDecimal(String.valueOf(r.getFloat(i))).doubleValue();
					ex.add(value);
				}

			}

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
		ResultSet r = s.executeQuery(
				"SELECT " + column.getColumnName() + " FROM " + table + " order by " + column.getColumnName() + " asc");
		while (r.next()) {
			if (r.getObject(column.getColumnName()).getClass().equals(String.class)) {
				obj = r.getString(column.getColumnName());
			} else {
				obj = r.getFloat(column.getColumnName());
			}
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

		ResultSet r = s.executeQuery("SELECT " + aggregate.name() + "(" + column.getColumnName() + ") as "
				+ column.getColumnName() + " from " + table);
		if (r.next()) {
			if (!column.isNumber()) {
				obj = r.getString(column.getColumnName());
			} else {
				obj = r.getFloat(column.getColumnName());
			}
		}
		return obj;
	}

}
