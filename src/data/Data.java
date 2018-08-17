package data;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.Iterator;

import database.DbAccess;
import database.EmptyTypeException;
import database.Example;
import database.NoValueException;
import database.QUERY_TYPE;
import database.TableData;
import database.TableSchema;

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
	 * 
	 * @throws EmptyTypeException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws NoValueException
	 */
	public Data(String table) throws SQLException, EmptyTypeException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoValueException {
		DbAccess db = new DbAccess();

		TableData date = new TableData(db);
		this.data = date.getDistinctTransazioni(table);

		this.numberOfExamples = data.size();

		TableSchema init = new TableSchema(db, table);

		for (int i = 0; i < init.getNumberOfAttributes(); i++) {
			if (init.getColumn(i).isNumber()) {
				Float min = (float) date.getAggregateColumnValue(table, init.getColumn(i), QUERY_TYPE.MIN);
				Float max = (float) date.getAggregateColumnValue(table, init.getColumn(i), QUERY_TYPE.MAX);
				Double dmin = new BigDecimal(String.valueOf(min)).doubleValue();
				Double dmax = new BigDecimal(String.valueOf(max)).doubleValue();

				ContinuousAttribute temp = new ContinuousAttribute(init.getColumn(i).getColumnName(), i, (double) dmin,
						(double) dmax);
				this.explanatorySet.add(temp);
			} else {
				Object[] colonne = date.getDistinctColumnValues(table, init.getColumn(i)).toArray();
				String[] colonne2 = new String[colonne.length];
				for (int j = 0; j < colonne.length; j++) {
					colonne2[j] = (String) colonne[j];
				}
				DiscreteAttribute temp2 = new DiscreteAttribute(init.getColumn(i).getColumnName(), i, colonne2);
				this.explanatorySet.add(temp2);
			}
		}

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
			if (!(this.getAttributeValue(index, i) instanceof String)) {
				tuple.add((Item) new ContinuousItem(this.explanatorySet.get(i),
						(Double) this.getAttributeValue(index, i)), i);
			} else {
				tuple.add((Item) new DiscreteItem((DiscreteAttribute) this.explanatorySet.get(i),
						(String) this.getAttributeValue(index, i)), i);
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
	/*
	 * Tuple getItemSet(int index) { Tuple a = new
	 * Tuple(this.explanatorySet.size()); for(int i=0;
	 * i<this.explanatorySet.size();i++) { if(this.getAttributeValue(index, i)
	 * instanceof ContinuousAttribute) { a.add((Item)new
	 * ContinuousItem(this.explanatorySet.get(i),(Double)
	 * this.getAttributeValue(index, i)),i); }else { a.add((Item)new
	 * DiscreteItem((DiscreteAttribute) this.explanatorySet.get(i),(String)
	 * this.getAttributeValue(index, i)),i); } } return a;
	 * 
	 * }
	 */

}
