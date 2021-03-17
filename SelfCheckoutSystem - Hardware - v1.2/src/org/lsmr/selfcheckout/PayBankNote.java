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
	private BanknoteStorageUnit capacity = new BanknoteStorageUnit(500);
	private Banknote banknote = new Banknote(0, null);
	
	/**
	 * 
	 * @param banknote
	 * 			Allows the user to pay for their items with banknotes
	 * 
	 * @throws DisabledException
	 * 			If the storageunit is disabled
	 * 
	 * @throws OverloadException
	 * 			If the storageunit is full
	 */			
	public PayBankNote(Banknote banknote) throws DisabledException, OverloadException {
		this.banknote = banknote;
		
		int[] denomination = {5,10,20,50,100};
		
		Currency cur = Currency.getInstance(Locale.CANADA);
		
		BanknoteValidator valid = new BanknoteValidator(cur, denomination);
		BanknoteSlot banknoteSlot = new BanknoteSlot(false);
		BidirectionalChannel<Banknote> ejection = new BidirectionalChannel<Banknote>(banknoteSlot, valid);
		UnidirectionalChannel<Banknote> accepted = new UnidirectionalChannel<Banknote>(valid);
		
		valid.connect(ejection, accepted);
		valid.accept(banknote);
		checkFullStorage();
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
