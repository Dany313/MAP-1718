package database;

public class EmptyTypeException extends Exception {
	public EmptyTypeException() {
		System.err.println("query vuota");
	}
}
