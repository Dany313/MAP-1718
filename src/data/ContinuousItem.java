package data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ContinuousItem extends Item {
	/**
	 * richiama il costruttore della super classe
	 * 
	 * @param attribute
	 * @param value
	 */
	public ContinuousItem(Attribute attribute, Double value) {
		super(attribute, value);
	}

	@Override
	/**
	 * Determina la distanza (in valore assoluto) tra il valore scalato memorizzato
	 * nello item corrente (this.getValue()) e quello scalato associato al parametro
	 * a. Per ottenere valori scalati fare uso di getScaledValue(...)
	 * 
	 */
	public double distance(Object a) {
		// ContinuousItem.class.cast(a);
		
		//ContinuousItem x = ContinuousItem.class.cast(a);
		double primo = 0, secondo = 0;
		try {
			Method m = ContinuousAttribute.class.getDeclaredMethod("getScaledValue", double.class);
			m.setAccessible(true);
			primo = (double) m.invoke(this.getAttribute(), this.getValue());
			secondo = (double) m.invoke(this.getAttribute(), a);

		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Math.abs(primo - secondo);
	}

}
