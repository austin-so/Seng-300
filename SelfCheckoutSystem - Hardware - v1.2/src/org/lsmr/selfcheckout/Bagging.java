package org.lsmr.selfcheckout;

import java.util.ArrayList;
import java.util.Arrays;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.ElectronicScale;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Bagging {

	/*
	 * get latest scanned item and related weight
	 */
	private double[] itemWeight;
	private BarcodedItem item;
	
	/**
	 * @param item 
	 * 		The item  that was first scanned 
	 */
	public Bagging(BarcodedItem item) {
		this.item = item;
	}
	
	/**
	 * @param itemWeight 
	 * 		The array of item weight, without any new item scanned
	 */
	public Bagging(double[] itemWeight) {
		this.itemWeight = itemWeight;
	}
	
	/**
	 * @param itemWeight 
	 * 		The array of item weight, without any new item scanned
	 */
	public Bagging(double[] itemWeight, BarcodedItem item) {
		this.itemWeight = itemWeight;
		this.item = item;
	}
	public void getItemWeight() {
		
		ScanItem scannedItem = new ScanItem(item);
		ArrayList<BarcodedItem> temp = scannedItem .getStoreItem();
		
		for(int x=0; x<temp.size(); x++) {
			BarcodedItem i = temp.get(x);
			double Weight = i.getWeight();
			itemWeight[x] = Weight;
		}
		
	}
	
	public int getTotalWeight() {
		int totalWeight = 0;
		for(int x=0; x<itemWeight.length; x++) {
			totalWeight += itemWeight[x];
		}
		return totalWeight; 
	}
	
	/*
	 * if scale detect added weight +- sensitivity, signal
	 */
	
	/*
	 * if scale detect remove, throw exception
	 */
	public void baggingProcess() {
		
		
	}
}
