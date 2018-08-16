package data;

import java.io.Serializable;

/**
 * 
 * estende la classe Item e rappresenta una coppia <Attributo discreto- valore
 * discreto> (per esempio Outlook=”Sunny”)
 *
 * 
 */
public class DiscreteItem extends Item implements Serializable {
	/**
	 * Invoca il costruttore della classe madre
	 * 
	 * @param attribute
	 * @param value
	 */
	public DiscreteItem(DiscreteAttribute attribute, String value) {
		super(attribute, value);
	}

	@Override
	/**
	 * Restituisce 0 se (getValue().equals(a)) , 1 altrimenti.
	 */
	public double distance(Object a) {
		if (this.getValue().equals(a)) {
			return 0;
		} else {
			return 1;
		}
	}

}
