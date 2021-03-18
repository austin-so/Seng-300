package org.lsmr.selfcheckout;

import java.util.ArrayList;
import java.util.Arrays;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Bagging {

	/*
	 * get latest scanned item and related weight
	 */
	private BarcodedItem item;
	
	/**
	 * @param item 
	 * 		The item  that was first scanned 
	 */
	public Bagging(BarcodedItem item) {
		this.item = item;
	}
	
	/**
	 * 
	 * @return item
	 * 		return the item being bagged
	 */
	public Item getBaggingItem() {
		return item;
	}

	public double getItemWeight(int weightLimitInGrams, int sensitivity) throws OverloadException {
		return item.getWeight();
	}
	
	public void baggingProcess(int weightLimitInGrams, int sensitivity) throws OverloadException {
		ElectronicScale scale = new ElectronicScale( weightLimitInGrams,  sensitivity);

		scale.enable();
		
		//if current weight > weight limit in grams, then it will throw exception
		
		if(scale.getCurrentWeight() < weightLimitInGrams) {
			scale.add(item);
		}
		else if(scale.getCurrentWeight() > weightLimitInGrams) {
			scale.remove(item);

		}
		else {
		}
		
	}


}
