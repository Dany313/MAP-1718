package mining;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import data.Data;
import data.OutOfRangeSampleSize;

/**
 * 
 * include l'implementazione dell’algoritmo kmeans
 *
 */
public class KMeansMiner implements Serializable {

	ClusterSet C;

	/**
	 * Apre il file identificato da fileName, legge l'oggetto ivi memorizzato e lo
	 * assegna a C.
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public KMeansMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream inFile = new FileInputStream(fileName);
		ObjectInputStream inStream = new ObjectInputStream(inFile);
		KMeansMiner Kmeans = (KMeansMiner) inStream.readObject();
		this.C = Kmeans.getC();
		inFile.close();
		
	}

	/**
	 * Crea l'oggetto array riferito da C
	 * 
	 * @param k numero di cluster da generare
	 */
	public KMeansMiner(int k) {
		this.C = new ClusterSet(k);
	}

	/**
	 * restituisce C
	 * 
	 * @return
	 */
	public ClusterSet getC() {
		return this.C;

	}

	/**
	 * Apre il file identificato da fileName e sarva l'oggetto riferito da C in tale file. 
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salva(String fileName) throws FileNotFoundException, IOException {
		FileOutputStream outFile = new FileOutputStream(fileName);
		ObjectOutputStream outStream = new ObjectOutputStream(outFile); 
		outStream.writeObject(this);
		outStream.close();
		
	}

	/**
	 * Esegue l’algoritmo k-means eseguendo i passi dello pseudo-codice: 1. Scelta
	 * casuale di centroidi per k clusters 2. Assegnazione di ciascuna riga della
	 * matrice in data al cluster avente centroide più vicino all'esempio. 3.
	 * Calcolo dei nuovi centroidi per ciascun cluster 4. Ripete i passi 2 e 3.
	 * finché due iterazioni consecuitive non restituiscono centroidi uguali .
	 * 
	 * @param data
	 * @return numero di iterazioni eseguite
	 */
	public int kmeans(Data data) throws OutOfRangeSampleSize {
		int numberOfIterations = 0;
		// STEP 1
		C.initializeCentroids(data);
		boolean changedCluster = false;
		do {
			numberOfIterations++;
			// STEP 2
			changedCluster = false;
			for (int i = 0; i < data.getNumberOfExamples(); i++) {
				Cluster nearestCluster = C.nearestCluster(data.getItemSet(i));
				Cluster oldCluster = C.currentCluster(i);
				boolean currentChange = nearestCluster.addData(i);
				if (currentChange)
					changedCluster = true;
				// rimuovo la tupla dal vecchio cluster
				if (currentChange && oldCluster != null)
					// il nodo va rimosso dal suo vecchio cluster
					oldCluster.removeTuple(i);
			}
			// STEP 3
			C.updateCentroids(data);
		} while (changedCluster);
		return numberOfIterations;

	}
}
