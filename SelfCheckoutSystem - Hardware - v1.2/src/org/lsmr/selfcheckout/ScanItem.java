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
private ArrayList<Barcode> storeItemCode; 
	
	/**
	 * 
	 * @param item
	 * 			the item that the costumer wants to scan
	 */
	public ScanItem(BarcodedItem item) {
		BarcodeScanner bars = new BarcodeScanner();
		bars.scan(item);
		
		this.item = item;
	}
	
	/**
	 * Store the items barcode into an arraylist
	 */
	public void storeItemCode() {
		Barcode itemcode = item.getBarcode(); 
		storeItemCode.add(itemcode);
	}
	/**
	* return the getStoreItemCode arraylist
	*/
	public ArrayList<Barcode> getStoreItemCode() {
		return storeItemCode;
	}
}

}
