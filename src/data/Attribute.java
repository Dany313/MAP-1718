package data;
/**
 * 
 * modella la entità attributo.
 *
 */
public abstract class Attribute {
	/**
	 * nome simbolico dell'attributo
	 */
	protected String name;
	/**
	 * identificativo numerico dell'attributo
	 */
	protected int index;

	/**
	 * inizializza i valori dei membri name, index
	 * 
	 * @param name
	 *            nome dell'attributo
	 * @param index
	 *            identificativo numerico dell'attributo
	 */
	public Attribute(String name, int index) {
		this.name = name;
		this.index = index;
	}

	/**
	 * restituisce name;
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * restituisce index;
	 * 
	 * @return index
	 */
	public int getIndex() {
		return this.index;
	}

	@Override
	/**
	 * sovrascrive metodo ereditato dalla superclasse e restuisce la stringa
	 * rappresentante lo stato dell'oggetto, restituisce name;
	 */
	public String toString() {
		return this.name;
	}

}
