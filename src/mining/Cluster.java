package mining;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import data.Data;
import data.Tuple;

public class Cluster implements Serializable{
	private Tuple centroid;

	// private ArraySet clusteredData;
	Set<Integer> clusteredData = new HashSet<Integer>();

	/*
	 * Cluster(){
	 * 
	 * }
	 */

	Cluster(Tuple centroid) {
		this.centroid = centroid;
		// clusteredData=new ArraySet();

	}

	Tuple getCentroid() {
		return centroid;
	}

	void computeCentroid(Data data) {
		for (int i = 0; i < centroid.getLength(); i++) {
			centroid.get(i).update(data, clusteredData);

		}

	}

	// return true if the tuple is changing cluster
	boolean addData(int id) {
		return clusteredData.add(id);

	}

	// verifica se una transazione � clusterizzata nell'array corrente
	boolean contain(int id) {
		return clusteredData.contains(id);
	}

	// remove the tuple that has changed the cluster
	void removeTuple(int id) {

		this.clusteredData.remove(id);

	}

	public String toString() {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++)
			str += centroid.get(i);
		str += ")";
		return str;

	}

	public String toString(Data data) {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++)
			str += centroid.get(i) + " ";
		str += ")\nExamples:\n";
		Object[] array = clusteredData.toArray();
		for (int i = 0; i < array.length; i++) {
			str += "[";
			for (int j = 0; j < data.getNumberOfExplanatoryAttributes(); j++)
				str += data.getAttributeValue((int) array[i], j) + " ";
			str += "] dist=" + getCentroid().getDistance(data.getItemSet((int) array[i])) + "\n";

		}
		str += "\nAvgDistance=" + getCentroid().avgDistance(data, array);
		return str;

	}

}