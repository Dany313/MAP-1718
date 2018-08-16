package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Data {
	/**
	 * una matrice nXm di tipo Object dove ogni riga modella una transazioni
	 */
	// private Object data[][];
	List<Example> data = new ArrayList<Example>();
	/**
	 * cardinalità dell’insieme di transazioni (numero di righe in data)
	 */
	private int numberOfExamples;
	/**
	 * un vettore degli attributi in ciascuna tupla (schema della tabella di dati)
	 * 
	 */
	// private Attribute attributeSet[];

	List<Attribute> explanatorySet = new LinkedList<Attribute>();
	/**
	 * numero di tuple distinte
	 */
	// private int distinctTuples;

	/**
	 * : Inizializza la matrice data [ ][ ] con transazioni di esempio (in questo
	 * momento, 14 esempi e 5 attributi come riportato nella tabella sottostante);
	 * Inizializza attributeSet creando cinque oggetti di tipo DiscreteAttribute,
	 * uno per ciascun attributo (nella tabella sottostante). Attenzione a modellare
	 * correttamente, nome, indice e dominio di ciascun attributo. Inizializza
	 * numberOfExamples
	 */
	public Data() {

		String outLookValues[] = new String[3];
		outLookValues[0] = "overcast";
		outLookValues[1] = "rain";
		outLookValues[2] = "sunny";
		this.explanatorySet.add(new DiscreteAttribute("Outlook", 0, outLookValues));

		/*
		 * String TemperatureValues[] = new String[3]; TemperatureValues[0] = "hot";
		 * TemperatureValues[1] = "mild"; TemperatureValues[2] = "cool";
		 * this.explanatorySet.add(new DiscreteAttribute("Temperature", 1,
		 * TemperatureValues));
		 */
		explanatorySet.add(new ContinuousAttribute("Temperature", 1, 3.2, 38.7));

		String HumidityValues[] = new String[2];
		HumidityValues[0] = "high";
		HumidityValues[1] = "normal";
		this.explanatorySet.add(new DiscreteAttribute("Humidity", 2, HumidityValues));

		String WindValues[] = new String[2];
		WindValues[0] = "weak";
		WindValues[1] = "strong";
		this.explanatorySet.add(new DiscreteAttribute("Wind", 3, WindValues));

		String PlayValues[] = new String[2];
		PlayValues[0] = "yes";
		PlayValues[1] = "no";
		this.explanatorySet.add(new DiscreteAttribute("PlayTennis", 4, PlayValues));

		/*
		 * this.data = new Object[14][5]; data[0][0] = "sunny"; data[0][1] = "hot";
		 * data[0][2] = "high"; data[0][3] = "weak"; data[0][4] = "no";
		 * 
		 * data[1][0] = "sunny"; data[1][1] = "hot"; data[1][2] = "high"; data[1][3] =
		 * "strong"; data[1][4] = "no";
		 * 
		 * data[2][0] = "overcast"; data[2][1] = "hot"; data[2][2] = "high"; data[2][3]
		 * = "weak"; data[2][4] = "yes";
		 * 
		 * data[3][0] = "rain"; data[3][1] = "mild"; data[3][2] = "high"; data[3][3] =
		 * "weak"; data[3][4] = "yes";
		 * 
		 * data[4][0] = "rain"; data[4][1] = "cool"; data[4][2] = "normal"; data[4][3] =
		 * "weak"; data[4][4] = "yes";
		 * 
		 * data[5][0] = "rain"; data[5][1] = "cool"; data[5][2] = "normal"; data[5][3] =
		 * "strong"; data[5][4] = "no";
		 * 
		 * data[6][0] = "overcast"; data[6][1] = "cool"; data[6][2] = "normal";
		 * data[6][3] = "strong"; data[6][4] = "yes";
		 * 
		 * data[7][0] = "sunny"; data[7][1] = "mild"; data[7][2] = "high"; data[7][3] =
		 * "weak"; data[7][4] = "no";
		 * 
		 * data[8][0] = "sunny"; data[8][1] = "cool"; data[8][2] = "normal"; data[8][3]
		 * = "Weak"; data[8][4] = "yes";
		 * 
		 * data[9][0] = "rain"; data[9][1] = "mild"; data[9][2] = "normal"; data[9][3] =
		 * "weak"; data[9][4] = "yes";
		 * 
		 * data[10][0] = "sunny"; data[10][1] = "mild"; data[10][2] = "normal";
		 * data[10][3] = "strong"; data[10][4] = "yes";
		 * 
		 * data[11][0] = "overcast"; data[11][1] = "mild"; data[11][2] = "high";
		 * data[11][3] = "strong"; data[11][4] = "yes";
		 * 
		 * data[12][0] = "overcast"; data[12][1] = "hot"; data[12][2] = "normal";
		 * data[12][3] = "weak"; data[12][4] = "yes";
		 * 
		 * data[13][0] = "rain"; data[13][1] = "mild"; data[13][2] = "high"; data[13][3]
		 * = "strong"; data[13][4] = "no";
		 */

		TreeSet<Example> tempData = new TreeSet<Example>();
		Example ex0 = new Example();
		Example ex1 = new Example();
		Example ex2 = new Example();
		Example ex3 = new Example();
		Example ex4 = new Example();
		Example ex5 = new Example();
		Example ex6 = new Example();
		Example ex7 = new Example();
		Example ex8 = new Example();
		Example ex9 = new Example();
		Example ex10 = new Example();
		Example ex11 = new Example();
		Example ex12 = new Example();
		Example ex13 = new Example();

		ex0.add(new String("sunny"));
		ex1.add(new String("sunny"));
		ex2.add(new String("overcast"));
		ex3.add(new String("rain"));
		ex4.add(new String("rain"));
		ex5.add(new String("rain"));
		ex6.add(new String("overcast"));
		ex7.add(new String("sunny"));
		ex8.add(new String("sunny"));
		ex9.add(new String("rain"));
		ex10.add(new String("sunny"));
		ex11.add(new String("overcast"));
		ex12.add(new String("overcast"));
		ex13.add(new String("rain"));

		/*
		 * ex0.add(new String ("hot")); ex1.add(new String ("hot")); ex2.add(new String
		 * ("hot")); ex3.add(new String ("mild")); ex4.add(new String ("cool"));
		 * ex5.add(new String ("cool")); ex6.add(new String ("cool")); ex7.add(new
		 * String ("mild")); ex8.add(new String ("cool")); ex9.add(new String ("mild"));
		 * ex10.add(new String ("mild")); ex11.add(new String ("mild")); ex12.add(new
		 * String ("hot")); ex13.add(new String ("mild"));
		 */
		ex0.add(new Double(37.5));
		ex1.add(new Double(38.7));
		ex2.add(new Double(37.5));
		ex3.add(new Double(20.5));
		ex4.add(new Double(20.7));
		ex5.add(new Double(21.2));
		ex6.add(new Double(20.5));
		ex7.add(new Double(21.2));
		ex8.add(new Double(21.2));
		ex9.add(new Double(19.8));
		ex10.add(new Double(3.5));
		ex11.add(new Double(3.6));
		ex12.add(new Double(3.5));
		ex13.add(new Double(3.2));

		ex0.add(new String("high"));
		ex1.add(new String("high"));
		ex2.add(new String("high"));
		ex3.add(new String("high"));
		ex4.add(new String("normal"));
		ex5.add(new String("normal"));
		ex6.add(new String("normal"));
		ex7.add(new String("high"));
		ex8.add(new String("normal"));
		ex9.add(new String("normal"));
		ex10.add(new String("normal"));
		ex11.add(new String("high"));
		ex12.add(new String("normal"));
		ex13.add(new String("high"));

		ex0.add(new String("weak"));
		ex1.add(new String("strong"));
		ex2.add(new String("weak"));
		ex3.add(new String("weak"));
		ex4.add(new String("weak"));
		ex5.add(new String("strong"));
		ex6.add(new String("strong"));
		ex7.add(new String("weak"));
		ex8.add(new String("weak"));
		ex9.add(new String("weak"));
		ex10.add(new String("strong"));
		ex11.add(new String("strong"));
		ex12.add(new String("weak"));
		ex13.add(new String("strong"));

		ex0.add(new String("no"));
		ex1.add(new String("no"));
		ex2.add(new String("yes"));
		ex3.add(new String("yes"));
		ex4.add(new String("yes"));
		ex5.add(new String("no"));
		ex6.add(new String("yes"));
		ex7.add(new String("no"));
		ex8.add(new String("yes"));
		ex9.add(new String("yes"));
		ex10.add(new String("yes"));
		ex11.add(new String("yes"));
		ex12.add(new String("yes"));
		ex13.add(new String("no"));

		tempData.add(ex0);
		tempData.add(ex1);
		tempData.add(ex2);
		tempData.add(ex3);
		tempData.add(ex4);
		tempData.add(ex5);
		tempData.add(ex6);
		tempData.add(ex7);
		tempData.add(ex8);
		tempData.add(ex9);
		tempData.add(ex10);
		tempData.add(ex11);
		tempData.add(ex12);
		tempData.add(ex13);

		data = new ArrayList<Example>(tempData);

		this.numberOfExamples = this.data.size();

		// numberOfExamples

		numberOfExamples = 14;

		// this.distinctTuples = this.countDistinctTuples();
		// System.out.println(this.distinctTuples);

	}

	/**
	 * restituisce numberOfExamples
	 * 
	 * 
	 * @return cardinalità dell'insieme di transazioni
	 */
	public int getNumberOfExamples() {
		return this.numberOfExamples;
	}

	/**
	 * restituisce la dimensione di explanatorySet
	 * 
	 * @return cardinalità dell'insieme degli attributi
	 */
	public int getNumberOfExplanatoryAttributes() {
		return this.explanatorySet.size();
	}

	/**
	 * restituisce explanatorySet
	 * 
	 * @return restituisce lo schema dei dati
	 */
	public List<Attribute> getAttributeSchema() {
		return this.explanatorySet;
	}

	/**
	 * restituisce data[exampleIndex][attributeIndex]
	 * 
	 * @param exampleIndex
	 *            indice di riga
	 * @param attributeIndex
	 *            indice di colonna
	 * @return valore assunto in data dall'attributo in posizione attributeIndex,
	 *         nella riga in posizione exampleIndex
	 */
	public Object getAttributeValue(int exampleIndex, int attributeIndex) {
		return this.data.get(exampleIndex).get(attributeIndex);
	}

	@Override
	/**
	 * crea una stringa in cui memorizza lo schema della tabella (vedi
	 * explanatorySet) e le transazioni memorizzate in data, opportunamente
	 * enumerate. Restituisce tale stringa
	 * 
	 * @return stringa che modella lo stato dell'oggetto
	 */
	public String toString() {
		String out = new String();
		for (Attribute a : this.explanatorySet) {
			out += a + ",";
		}

		for (int i = 0; i < this.getNumberOfExamples(); i++) {
			out += "\n" + (i + 1) + ":";
			for (int j = 0; j < this.getNumberOfExplanatoryAttributes(); j++) {
				out += this.getAttributeValue(i, j) + ",";
			}

		}
		return out;

	}

	/**
	 * Crea e restituisce un oggetto di Tuple che modella come sequenza di coppie
	 * Attributo-valore la i-esima riga in data.
	 * 
	 * @param index
	 *            indice di riga
	 * @return
	 */
	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(this.getNumberOfExplanatoryAttributes());
		for (int i = 0; i < this.getNumberOfExplanatoryAttributes(); i++) {
			if(!(this.getAttributeValue(index, i) instanceof String)) {
				tuple.add((Item)new ContinuousItem(this.explanatorySet.get(i),(Double) this.getAttributeValue(index, i)),i);
			}else {
				tuple.add((Item)new DiscreteItem((DiscreteAttribute) this.explanatorySet.get(i),(String) this.getAttributeValue(index, i)),i);
			}
		}
		return tuple;
	}

	/**
	 * 
	 * @param k
	 *            numero di cluster da generare
	 * @return array, di k interi rappresentanti gli indici di riga in data per le
	 *         tuple inizialmente scelte come centroidi (passo 1 del k-means)
	 */
	public int[] sampling(int k) throws OutOfRangeSampleSize {
		if (k <= 0 || k > this.data.size()) {
			throw new OutOfRangeSampleSize(k);
		}
		int centroidIndexes[] = new int[k];
		// choose k random different centroids in data.
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i = 0; i < k; i++) {
			boolean found = false;
			int c;
			do {
				found = false;
				c = rand.nextInt(getNumberOfExamples());
				// verify that centroid[c] is not equal to a centroide
				// already stored in CentroidIndexes
				for (int j = 0; j < i; j++)
					if (compare(centroidIndexes[j], c)) {
						found = true;
						break;
					}
			} while (found);
			centroidIndexes[i] = c;
		}
		return centroidIndexes;

	}

	/**
	 * restituisce vero se le due righe di data contengono gli stessi valori, falso
	 * altrimenti
	 * 
	 * @param i
	 *            indice di riga 1
	 * @param j
	 *            indice di riga 2
	 * @return
	 */
	private boolean compare(int i, int j) {
		boolean ris = true;
		for (int x = 0; x < this.getNumberOfExplanatoryAttributes(); x++) {
			if (!this.getAttributeValue(i, x).equals(this.getAttributeValue(j, x))) {
				ris = false;
			}
		}
		return ris;

	}

	/**
	 * restituisce computePrototype(idList, (DiscreteAttribute)attribute)
	 * 
	 * @param clusteredData
	 *            insieme di indici di riga
	 * @param attribute
	 *            attributo rispetto al quale calcolare il prototipo (centroide)
	 * 
	 * @return valore centroide rispetto ad attribute
	 */
	public Object computePrototype(Set<Integer> clusteredData, Attribute attribute) {
		// return computePrototype(clusteredData, (DiscreteAttribute) attribute);

		if (attribute instanceof DiscreteAttribute) {
			return computePrototype(clusteredData, (DiscreteAttribute) attribute);
		} else {
			return computePrototype(clusteredData, (ContinuousAttribute) attribute);
		}
	}

	/**
	 * Determina il valore che occorre più frequentemente per attribute nel
	 * sottoinsieme di dati individuato da idList (fare uso del metodo
	 * frequency(...) di DiscretAttribute).
	 * 
	 * @param idList
	 *            insieme degli indici delle righe di data appartenenti as un
	 *            cluster,
	 * @param attribute
	 *            attributo discreto rispetto al quale calcolare il prototipo
	 *            (centroide)
	 * 
	 * @return centroide rispetto ad attribute
	 */
	public String computePrototype(Set<Integer> idList, DiscreteAttribute attribute) {
		int max = 0;
		String valoreMax = new String();
		for (String a : attribute) {
			if (attribute.frequency(this, idList, a) > max) {
				max = attribute.frequency(this, idList, a);
				valoreMax = a;
			}
		}
		return valoreMax;

		/*
		 * for (int i = 0; i < attribute.getNumberOfDistinctValues(); i++) { String a =
		 * attribute.iterator().next(); if (attribute.frequency(this, idList,a) > max) {
		 * max = attribute.frequency(this, idList, a); valoreMax = a; } } return
		 * valoreMax;
		 */
	}

	/**
	 * Determina il valore prototipo come media dei valori osservati per attribute
	 * nelle transazioni di data aventi indice di riga in idList
	 * 
	 * @param idList
	 * @param attribute
	 * @return
	 */
	public Double computePrototype(Set<Integer> idList, ContinuousAttribute attribute) {
		double somma = 0;
		for (Integer i : idList) {
			somma += (double) this.getAttributeValue(i, attribute.index);
		}

		return somma / idList.size();
	}

	/**
	 * Conta il numero di transazioni distinte memorizzate in data (fare uso del
	 * metodo boolean compare(int,int))
	 * 
	 * @return Numero di transazioni distinte memorizzate nella matrice data
	 */
	/*
	 * private int countDistinctTuples() { int distTuples = 1; boolean ris = true;
	 * for (int i = 1; i < this.getNumberOfExamples(); i++) { ris = true; for (int j
	 * = i - 1; j >= 0; j--) { if (this.compare(i, j)) { ris = false; } } if (ris ==
	 * true) { distTuples++; } } return distTuples;
	 * 
	 * }
	 */

	/**
	 * Crea e un istanza di Tuple che modelli la transazione con indice di riga
	 * index in data. Restituisce il riferimento a tale istanza. Usare lo RTTI per
	 * distinguere tra ContinuousAttribute e DiscreteAttribute (e quindi creare
	 * nella tupla un ContinuousItem o un DiscreteItem)
	 * 
	 * @param index
	 * @return
	 */
	/*Tuple getItemSet(int index) {
		Tuple a = new Tuple(this.explanatorySet.size());
		for(int i=0; i<this.explanatorySet.size();i++) {
			if(this.getAttributeValue(index, i) instanceof ContinuousAttribute) {
				a.add((Item)new ContinuousItem(this.explanatorySet.get(i),(Double) this.getAttributeValue(index, i)),i);
			}else {
				a.add((Item)new DiscreteItem((DiscreteAttribute) this.explanatorySet.get(i),(String) this.getAttributeValue(index, i)),i);
			}
		}
		return a;

	}*/

	class Example implements Comparable<Example> {
		/**
		 * array di Object che rappresentano la singola transazione (o riga di una
		 * tabella)
		 */
		List<Object> example = new ArrayList<Object>();

		/**
		 * aggiunge o in coda ad example
		 * 
		 * @param o
		 *            ogetto da aggiungere
		 */
		void add(Object o) {
			this.example.add(o);
		}

		/**
		 * restituisce lo i-esimo riferimento collezionato in example
		 * 
		 * @param i
		 * @return
		 */
		Object get(int i) {
			return this.example.get(i);
		}

		@Override
		/**
		 * restituisce 0, -1, 1 sulla base del risultato del confronto. 0 se i due
		 * esempi includono gli stessi valori. Altrimenti il risultato del
		 * compareTo(...) invocato sulla prima coppia di valori in disaccordo.
		 */
		public int compareTo(Example arg0) {
			for (int i = 0; i < this.example.size(); i++) {
				if (!this.example.get(i).equals(arg0.example.get(i))) {
					if (this.example.get(i).toString().length() < arg0.example.get(i).toString().length()) {
						return -1;
					} else {
						return 1;
					}

				}
			}
			return 0;
		}

		/**
		 * restutuisce una stringa che rappresenta lo stato di example (fare uso di
		 * for-each)
		 */
		public String toString() {
			String out = "";
			for (Object a : this.example) {
				out += (String) a + ",";
			}
			return out;
		}

	}

}
