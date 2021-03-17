package org.lsmr.selfcheckout;

import java.util.Arrays;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.ElectronicScale;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Bagging {

	/*
	 * get latest scanned item and related weight
	 */
	private Array[] itemWeight;
	
	public void getItemWeight() {
		
		ScanItem scannedItem = new ScanItem();
		Array[] temp = scannedItem.getStoredItem();
		
		for(int x=0; x<itemWeight.length; x++) {
			BarcodedItem i = temp[x];
			double Weight = i.getWeight();
			itemWeight[x] = Weight;
		}
		
	}
	
	/*
	 * if scale detect added weight +- sensitivity, signal
	 */
	
	/*
	 * if scale detect remove, throw exception
	 */
	public void baggingProcess() {
		ElctronicScale
		
	}
}
