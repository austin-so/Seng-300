package org.lsmr.selfcheckout;



import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.BidirectionalChannel;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.UnidirectionalChannel;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteSlotListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteStorageUnitListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteValidatorListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Currency;
import java.util.Locale;

public class PayBankNoteTest {
	private int counter;
	
	@Before
	public void setCounter() {
		counter = 0;	
	}
	
	
	@Test
	public void fullBanknoteTest() throws DisabledException, OverloadException {
		PayBankNote five = new PayBankNote(new Banknote(5,Currency.getInstance(Locale.CANADA)));
		BanknoteStorageUnit capacity = five.getCapacity();
		capacity.register(new BanknoteStorageUnitListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesFull(BanknoteStorageUnit unit) {
				counter++;
				
			}

			@Override
			public void banknoteAdded(BanknoteStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesLoaded(BanknoteStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesUnloaded(BanknoteStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}
			
		});
		five.acceptintoStorage();
		five.acceptintoStorage();
		five.acceptintoStorage();
		five.acceptintoStorage();
		five.acceptintoStorage();
		if(counter > 0) {
			assertTrue(true);
		}
		else {
			fail();
		}
		
	}


	@Test
	public void fivedollartest() throws DisabledException, OverloadException {
		Currency cur =  Currency.getInstance(Locale.CANADA);
		PayBankNote five = new PayBankNote(new Banknote(5, cur));
		BanknoteValidator valid = five.getValid();
		valid.register(new BanknoteValidatorListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
				counter++;
				
			}

			@Override
			public void invalidBanknoteDetected(BanknoteValidator validator) {
				fail();
				
			}
			
		});
		five.acceptBankNote();
	}
	
	@Test
	public void tendollartest() throws DisabledException, OverloadException {
		Currency cur =  Currency.getInstance(Locale.CANADA);
		PayBankNote ten = new PayBankNote(new Banknote(10, cur));
		BanknoteValidator valid = ten.getValid();
		valid.register(new BanknoteValidatorListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
				counter++;
				
			}

			@Override
			public void invalidBanknoteDetected(BanknoteValidator validator) {
				fail();
				
			}
			
		});
		ten.acceptBankNote();
	}
	
	
	@Test
	public void differentCurrencyTest() throws DisabledException, OverloadException {
		Currency cur = Currency.getInstance(Locale.CHINA);
		PayBankNote five = new PayBankNote(new Banknote(5, cur));
		BanknoteValidator valid = five.getValid();
		valid.register(new BanknoteValidatorListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				fail();
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				fail();
				
			}

			@Override
			public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
				fail();
				
			}

			@Override
			public void invalidBanknoteDetected(BanknoteValidator validator) {
				counter++;
			}
			
		});
		five.acceptBankNote();
		if(counter > 0) {
			assertTrue(true);
		}
		else {
			fail();
		}
		
	}
	
	@Test
	public void twodollarTest() throws DisabledException, OverloadException {
		Currency cur = Currency.getInstance(Locale.CHINA);
		PayBankNote two = new PayBankNote(new Banknote(2, cur));
		BanknoteValidator valid = two.getValid();
		valid.register(new BanknoteValidatorListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				fail();
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				fail();
				
			}

			@Override
			public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
				fail();
				
			}

			@Override
			public void invalidBanknoteDetected(BanknoteValidator validator) {
				counter++;
			}
			
		});
		two.acceptBankNote();
		if(counter > 0) {
			assertTrue(true);
		}
		else {
			fail();
		}
	}
	
	@Test
	public void unloadBanknoteTest() throws DisabledException, OverloadException {
		PayBankNote five = new PayBankNote(new Banknote(5,Currency.getInstance(Locale.CANADA)));
		BanknoteStorageUnit capacity = five.getCapacity();
		capacity.register(new BanknoteStorageUnitListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesFull(BanknoteStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknoteAdded(BanknoteStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesLoaded(BanknoteStorageUnit unit) {
				fail();
				
			}

			@Override
			public void banknotesUnloaded(BanknoteStorageUnit unit) {
				counter++;
				
			}
			
		});
		five.acceptintoStorage();
		five.acceptintoStorage();
		five.acceptintoStorage();
		five.acceptintoStorage();
		five.acceptintoStorage();
		five.acceptintoStorage();
		if(counter > 0) {
			assertTrue(true);
		}
		else {
			fail();
		}
		
	}
	
	@Test
	public void insertInSlotTest() throws DisabledException, OverloadException {
		PayBankNote ten = new PayBankNote(new Banknote(10,Currency.getInstance(Locale.CANADA)));
		BanknoteSlot slot = ten.getBanknoteSlot();
		slot.register(new BanknoteSlotListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknoteInserted(BanknoteSlot slot) {
				counter++;
				
			}

			@Override
			public void banknoteEjected(BanknoteSlot slot) {
				
				
			}

			@Override
			public void banknoteRemoved(BanknoteSlot slot) {
				
				
			}
			
		});
		ten.acceptBankNote();
		if(counter > 0) {
			assertTrue(true);
		}
		else {
			fail();
		}
	}
}