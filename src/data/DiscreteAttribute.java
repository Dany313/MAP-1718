package data;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;



/**
 * estende la classe Attribute e rappresenta un attributo discreto (categorico)
 * 
 *
 * 
 */
public class DiscreteAttribute extends Attribute implements Iterable<String>,Serializable {
	/**
	 * array di oggetti String, uno per ciascun valore del dominio discreto. I
	 * valori del dominio sono memorizzati in values seguendo un ordine
	 * lessicografico.
	 * 
	 */
	//private String values[];
	TreeSet<String> values = new  TreeSet<String>();

	/**
	 * Invoca il costruttore della classe madre e inizializza il membro values con
	 * il parametro in input.
	 * 
	 * @param name
	 * @param index
	 * @param values
	 */
	public DiscreteAttribute(String name, int index, String values[]) {
		super(name, index);
		for(String a : values) {
			this.values.add(a);
		}
		//this.values = values;
	}

	/**
	 * Restituisce la dimensione di values
	 * 
	 * @return numero di valori discreti nel dominio dell'attributo
	 */
	public int getNumberOfDistinctValues() {
		return this.values.size();
	}

	/**
	 * Restituisce values[i]
	 * 
	 * @param i
	 * @return
	 */
	//public String getValue(int i) {
		//return this.values[i];
	//}

	/**
	 * Determina il numero di volte che il valore v compare in corrispondenza
	 * dell'attributo corrente (indice di colonna) negli esempi memorizzati in data
	 * e indicizzate (per riga) da idList
	 * 
	 * @param data
	 *            riferimento ad un oggetto Data
	 * @param idList
	 *            riferimento ad un oggetto ArraySet (che mantiene l'insieme degli
	 *            indici di riga di alcune tuple memorizzate in data)
	 * @param v
	 *            valore discreto
	 * @return numero di occorrenze del valore discreto (intero)
	 */
	public int frequency(Data data, Set<Integer> idList, String v) {
		//Object[] temp = new int[0];
		int frequency = 0;
		Object[] temp = idList.toArray();
		for (int i = 0; i < temp.length; i++) {
			if (v.equals(data.getAttributeValue((int)temp[i], this.index))) {
				frequency++;
			}

		}
		return frequency;

	}

	@Override
	public Iterator<String> iterator() {
		return this.values.iterator();
	}
	
	/*public static void main(String args[]) {
		
		DiscreteAttribute a = new DiscreteAttribute("ciao",0,)
	}*/

}
