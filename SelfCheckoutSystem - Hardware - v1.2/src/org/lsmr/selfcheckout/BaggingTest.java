package org.lsmr.selfcheckout;

import static org.junit.Assert.*;

import org.junit.Test;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SimulationException;

import junit.framework.Assert;

public class BaggingTest {

	private Barcode barcodeBanana = new Barcode("333");
	private Barcode barcodeApple = new Barcode("333");
	private Barcode barcodeOrange = new Barcode("333");
	
	private BarcodedItem banana = new BarcodedItem(barcodeBanana, 6.9);
	private BarcodedItem apple = new BarcodedItem(barcodeApple, 9.81);
	private BarcodedItem orange = new BarcodedItem(barcodeOrange, 3.14);

	@Test
	public void testBagging1(){
		Bagging bag = new Bagging(apple);
		
		Item i = bag.getBaggingItem();
		assertEquals(i, apple);
	}
 
	@Test
	public void testBagging2(){
		Bagging bag = new Bagging(banana);
	
		Item i = bag.getBaggingItem();
		assertEquals(i, banana);
	}
	
	@Test
	public void testBagging3(){
		Bagging bag = new Bagging(orange);
	
		Item i = bag.getBaggingItem();
		assertNotEquals(i, apple);
	}
	
	@Test
	public void testBagging4(){
		Bagging bag = new Bagging(orange);
	
		Item i = bag.getBaggingItem();
		assertNotEquals(i, banana);
	}
	
	@Test
	public void testBagging5(){
		Bagging bag = new Bagging(apple);
		
		Item i = bag.getBaggingItem();
		assertNotEquals(i, banana);
	}
 
	@Test
	public void testBagging6(){
		Bagging bag = new Bagging(apple);
	
		Item i = bag.getBaggingItem();
		assertNotEquals(i, orange);
	}
	
	@Test
	public void testBagging7() throws OverloadException {
		ElectronicScale scale = new ElectronicScale(500,1);
		Bagging bag = new Bagging(apple);
		bag.baggingProcess(500, 1);
	
		double w = scale.getCurrentWeight();
		assertEquals(w, 9.81, 0.001);
	}
	
	@Test
	public void testBagging8() throws OverloadException{
		ElectronicScale scale = new ElectronicScale(500,1);
		Bagging bag = new Bagging(orange);
		bag.baggingProcess(500, 1);
	
		double w = scale.getCurrentWeight();
		assertEquals(w, 3.14, 0.001);
	}

	@Test
	public void testBagging9() throws OverloadException {
		ElectronicScale scale = new ElectronicScale(1,5);
		Bagging bag = new Bagging(apple);
		bag.baggingProcess(1, 5);
	
		double w = scale.getCurrentWeight();
		assertEquals(w, 0, 0.001);
	}
	
	@Test
	public void testBagging10() throws OverloadException{
		ElectronicScale scale = new ElectronicScale(1,5);
		Bagging bag = new Bagging(orange);
		bag.baggingProcess(1, 5);
	
		double w = scale.getCurrentWeight();
		assertEquals(w, 0, 0.001);
	}
}
