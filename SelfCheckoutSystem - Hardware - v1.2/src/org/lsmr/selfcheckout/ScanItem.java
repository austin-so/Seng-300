package org.lsmr.selfcheckout;

import java.util.ArrayList;

import org.lsmr.selfcheckout.devices.BarcodeScanner;

/**
 * 
 * Scan an item that has a barcode
 *
 */
public class ScanItem {
	private BarcodedItem item;
	private ArrayList<Barcode> itemCode; 
	private ArrayList<BarcodedItem> allItems;
	
	/**
	 * 
	 * @param item
	 * 			the item that the costumer wants to scan
	 */
	
	public ScanItem(BarcodedItem item) {
		this.item = item;
	}
	
	/**
	 * Store the items barcode into an arraylist
	 */
	
	public void storeItemCode() {
		Barcode itemCode = item.getBarcode(); 
		this.itemCode.add(itemCode);
	}
	
	public void storeItem() {
		allItems.add(item);
	}
	
	/**
	 * scan the customers item
	 */
	
	public void scanAttempt() {
		BarcodeScanner bars = new BarcodeScanner();
		bars.scan(item);
	}
	
	/**
	* return the getStoreItemCode arraylist
	*/
	
	public ArrayList<Barcode> getItemCode() {
		return itemCode;
	}
	
	public ArrayList<BarcodedItem> getStoreItem(){
		return allItems;
	}
}


