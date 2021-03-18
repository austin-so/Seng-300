package org.lsmr.selfcheckout;



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
import org.lsmr.selfcheckout.devices.listeners.BanknoteValidatorListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Currency;
import java.util.Locale;

public class PayBankNoteTest {
	
	@Test
	public void Storagetest() throws DisabledException, OverloadException {
		PayBankNote five = new PayBankNote(new Banknote(5,Currency.getInstance(Locale.CANADA)));
		five.checkFullStorage();
	}


	@Test
	public void fivedollartest() throws DisabledException, OverloadException {
		Currency cur =  Currency.getInstance(Locale.CANADA);
		PayBankNote five = new PayBankNote(new Banknote(5, cur));
		five.acceptBankNote();
	}
	
	@Test
	public void differentCurrencyTest() throws DisabledException, OverloadException {
		Currency cur = Currency.getInstance(Locale.CHINA);
		PayBankNote five = new PayBankNote(new Banknote(5, cur));
		five.acceptBankNote();
	}
}