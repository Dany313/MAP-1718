package data;

import java.io.Serializable;

/**
 * 
 * rappresenta una tupla come sequenza di coppie attributo-valore.
 *
 * 
 */
public class Tuple implements Serializable{
	/**
	 * sequenza di coppie attributo-valore
	 */
	private Item[] tuple;

	/**
	 * costruisce l'oggetto riferito da tuple
	 * 
	 * @param size
	 *            numero di item che costituirà la tupla
	 */
	public Tuple(int size) {
		this.tuple = new Item[size];

	}

	/**
	 * restituisce tuple.length
	 * 
	 * @return
	 */
	public int getLength() {
		return tuple.length;
	}

	/**
	 * restituisce item in posizione i
	 * 
	 * @param i
	 * @return
	 */
	public Item get(int i) {
		return this.tuple[i];
	}

	/**
	 * memorizza c in tuple[i]
	 * 
	 * @param c
	 * @param i
	 */
	public void add(Item c, int i) {
		this.tuple[i] = c;

	}

	/**
	 * determina la distanza tra la tupla riferita da obj e la tupla corrente
	 * (riferita da this). La distanza è ottenuta come la somma delle distanze tra
	 * gli item in posizioni eguali nelle due tuple. Fare uso di double
	 * distance(Object a) di Item
	 * 
	 * @param obj
	 * @return
	 */
	public double getDistance(Tuple obj) {
		int distance = 0;
		for (int i = 0; i < this.getLength(); i++) {
			distance += this.get(i).distance(obj.get(i).getValue());
			
			
		}
		return distance;

	}

	/**
	 * restituisce la media delle distanze tra la tupla corrente e quelle ottenibili
	 * dalle righe della matrice in data aventi indice in clusteredData.
	 * 
	 * @param data
	 * @param array
	 * @return
	 */
	public double avgDistance(Data data, Object[] array) {
		double p = 0.0, sumD = 0.0;
		for (int i = 0; i < array.length; i++) {
			double d = getDistance(data.getItemSet((int)array[i]));
			sumD += d;
		}
		p = sumD / array.length;
		return p;

	}

}
