package org.lsmr.selfcheckout;

import java.util.Currency;
import java.util.Locale;

import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.BidirectionalChannel;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.UnidirectionalChannel;



public class PayBankNote {
	private BanknoteStorageUnit capacity = new BanknoteStorageUnit(5);
	private Banknote banknote = new Banknote(5, Currency.getInstance(Locale.CANADA));
	private int[] denominations = {5,10,20,50,100};
	private BanknoteValidator valid = new BanknoteValidator(Currency.getInstance(Locale.CANADA), denominations);
	
	/**
	 * 
	 * @param banknote
	 * 			Allows the user to pay for their items with banknotes
	 */			
	public PayBankNote(Banknote banknote) {
		this.banknote = banknote;
		
		BanknoteSlot banknoteSlot = new BanknoteSlot(false);
		BidirectionalChannel<Banknote> ejection = new BidirectionalChannel<Banknote>(banknoteSlot, valid);
		UnidirectionalChannel<Banknote> accepted = new UnidirectionalChannel<Banknote>(valid);
		
		valid.connect(ejection, accepted);
	}
	
	public void acceptBankNote() throws DisabledException {
		valid.accept(banknote);
	}
	
	/**
	 * 		Method to check if the storageunit is full
	 * @throws DisabledException
	 * 		If the storageunit is disabled
	 * @throws OverloadException
	 * 		If the storageunit is full
	 */
	public void checkFullStorage() throws DisabledException, OverloadException {
		capacity.accept(banknote);
	}

}
