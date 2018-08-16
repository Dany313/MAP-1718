package data;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



/**
 * 
 * modella un generico item (coppia attributo-valore, per esempio
 * Outlook=”Sunny”).
 *
 * 
 */
public abstract class Item implements Serializable{
	/**
	 * attributo coinvolto nell'item
	 */
	private Attribute attribute;
	/**
	 * valore assegnato all'attributo
	 */
	private Object value;

	/**
	 * inizializza i valori dei membri attributi
	 * 
	 * @param attribute
	 * @param value
	 */
	public Item(Attribute attribute, Object value) {
		this.attribute = attribute;
		this.value = value;
	}

	/**
	 * restituisce attribute;
	 * 
	 * @return restituisce attribute;
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * restituisce value;
	 * 
	 * @return restituisce value;
	 */
	public Object getValue() {
		return value;
	}

	@Override
	/**
	 * restituisce value
	 */

	public String toString() {
		return  this.value.toString();
	}

	/**
	 * L’implementazione sarà diversa per item discreto e item continuo
	 * 
	 * @param a
	 * @return
	 */
	public abstract double distance(Object a);

	/**
	 * Modifica il membro value, assegnandogli il valore restituito da
	 * data.computePrototype(clusteredData,attribute);
	 * 
	 * @param data
	 *            riferimento ad un oggetto della classe Data
	 * @param clusteredData
	 *            insieme di indici delle righe della matrice in data che formano il
	 *            cluster
	 */
	public void update(Data data, Set<Integer> clusteredData) {
		this.value = data.computePrototype(clusteredData, attribute);

	}

}
