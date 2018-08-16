package database;

/**
 * modella l’assenza di un valore all’interno di un resultset 
 * 
 * @author alexc
 *
 */
public class NoValueException extends Exception {
	public NoValueException() {
		System.err.println("query non contenente valori");
	}

}
