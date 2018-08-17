package data;

import java.io.Serializable;

/**
 * 
 * estende la classe Attribute e modella un attributo continuo (numerico). Tale
 * classe include i metodi per la “normalizzazione” del dominio dell'attributo
 * nell'intervallo [0,1] al fine da rendere confrontabili attributi aventi
 * domini diversi
 *
 */
public class ContinuousAttribute extends Attribute implements Serializable {
	/**
	 * estremo superiore del dominio
	 */
	private double max;
	/**
	 * estremo inferiore del dominio
	 */
	private double min;

	/**
	 * Invoca il costruttore della classe madre e inizializza i membri aggiunti per
	 * estensione
	 * 
	 * @param name
	 * @param index
	 * @param min
	 * @param max
	 */
	public ContinuousAttribute(String name, int index, double min, double max) {
		super(name, index);
		this.max = max;
		this.min = min;
	}

	/**
	 * Calcola e restituisce il valore normalizzato del parametro passato in input.
	 * La normalizzazione ha come codominio lo intervallo [0,1]. La normalizzazione
	 * di v è quindi calcolata come segue: v'=(v-min)/(max-min)
	 * 
	 * @param v
	 *            valore dell'attributo da normalizzare
	 * @return valore normalizzato
	 */
	public double getScaledValue(double v) {
		return (v - min) / (max - min);
	}

}
