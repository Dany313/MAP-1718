import java.sql.SQLException;

import data.Data;
import data.OutOfRangeSampleSize;
import database.EmptyTypeException;
import keyboardinput.Keyboard;
import mining.KMeansMiner;

public class MainTest {

	/**
	 * @param args
	 * @throws EmptyTypeException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, EmptyTypeException {

		Data data = new Data("mapdb");
		System.out.println(data);
		char esecuzione = 'y';
		while (esecuzione == 'y') {
			System.out.println("inserire numero di cluster");
			try {
				int k = Keyboard.readInt();

				KMeansMiner kmeans = new KMeansMiner(k);

				int numIter = kmeans.kmeans(data);

				System.out.println("Numero di Iterazione:" + numIter);
				System.out.println(kmeans.getC().toString(data));
			} catch (OutOfRangeSampleSize e) {

			}
			System.out.println("ripetere con un nuovo numero di cluster? (y/n)");
			esecuzione = Keyboard.readChar();
		}

	}

}
