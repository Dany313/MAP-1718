package mining;
import java.io.Serializable;

import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

/**
 * 
 * rappresenta un insieme di cluster (determinati da k-means)
 *
 * 
 */
public class ClusterSet implements Serializable{

	Cluster C[];
	/**
	 * posizione valida per la memorizzazione di un nuovo cluster in C
	 */
	int i = 0;

	/**
	 * creo l'oggetto array riferito da C
	 * 
	 * @param k
	 *            numero di cluster da generare (k-means)
	 */
	public ClusterSet(int k) {
		this.C = new Cluster[k];
	}

	/**
	 * assegna c a C[i ] e incrementa i
	 * 
	 * @param c
	 */
	public void add(Cluster c) {
		this.C[i] = c;
		this.i++;

	}

	/**
	 * restituisce C[i]
	 * 
	 * @param i
	 * @return
	 */
	Cluster get(int i) {
		return this.C[i];
	}

	/**
	 * sceglie i centroidi, crea un cluster per ogni centroide e lo memorizza in C
	 * 
	 * @param data
	 */
	public void initializeCentroids(Data data)  throws OutOfRangeSampleSize {
		int centroidIndexes[] = data.sampling(C.length);
		for (int i = 0; i < centroidIndexes.length; i++) {
			Tuple centroidI = data.getItemSet(centroidIndexes[i]);
			add(new Cluster(centroidI));
		}

	}

	/**
	 * Calcola la distanza tra la tupla riferita da tuple ed il centroide di ciascun
	 * cluster in C e restituisce il cluster più vicino (fare uso del metodo
	 * getDistance() della classe Tuple).
	 * 
	 * @param tuple
	 *            riferimento ad un oggetto Tuple
	 * @return cluster più “vicino” alla tupla passata
	 */
	public Cluster nearestCluster(Tuple tuple) {
		Cluster nearest = null;
		double min =10; //this.C[0].getCentroid().getDistance(tuple);
		for (Cluster x : this.C) {
			if (x.getCentroid().getDistance(tuple) < min) {
				min = x.getCentroid().getDistance(tuple);
				nearest = x;
			}
		}
		return nearest;

	}

	/**
	 * Identifica e restituisce il cluster a cui la tupla rappresentate l'esempio
	 * identificato da id. Se la tupla non è inclusa in nessun cluster restituisce
	 * null (fare uso del metodo contain() della classe Cluster).
	 * 
	 * @param id
	 *            indice di una riga nella matrice in Data
	 * @return
	 */
	public Cluster currentCluster(int id) {
		Cluster r = null;
		for (Cluster x : this.C) {
			if (x.contain(id)) {
				r = x;
			}
		}
		return r;
	}

	/**
	 * calcola il nuovo centroide per ciascun cluster in C (fare uso del metodo
	 * computeCentroid() della classe Cluster)
	 * 
	 * @param data
	 */
	public void updateCentroids(Data data) {
		for (Cluster x : this.C) {
			x.computeCentroid(data);
		}
	}

	@Override
	/**
	 * Restituisce una stringa fatta da ciascun centroide dell’insieme dei cluster.
	 */
	public String toString() {
		String a = "";
		for (Cluster x : this.C) {
			a += x.getCentroid().toString() + "\n";
		}
		return a;
	}

	/**
	 * Restituisce una stringa che descriva lo stato di ciascun cluster in C.
	 * 
	 * @param data
	 * @return
	 */
	public String toString(Data data) {
		String str = "";
		for (int i = 0; i < C.length; i++) {
			if (C[i] != null) {
				str += i + ":" + C[i].toString(data) + "\n";
			}
		}
		return str;

	}

}
