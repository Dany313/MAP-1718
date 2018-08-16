package data;

public class OutOfRangeSampleSize extends Exception {

	public OutOfRangeSampleSize(int k) {
		if (k == 0) {
			System.out.println("il numero di cluster inserito e' nullo");
		} else {
			System.out.println("il numero di cluster inserito e' maggiore al numero di tuple distinte ");
		}
	}

}
