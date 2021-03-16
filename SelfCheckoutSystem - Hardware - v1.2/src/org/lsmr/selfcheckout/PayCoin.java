package org.lsmr.selfcheckout;

  import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.UnidirectionalChannel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Currency;

public class PayCoin {
	private CoinStorageUnit capacity = new CoinStorageUnit(500);
	private Coin coin = new Coin(null, null);
	
	/**
	 * 
	 * @param coin
	 * 			Allows the user to pay for their items with coins
	 * 
	 * @throws DisabledException
	 * 			If the storageunit is disabled
	 * 
	 * @throws OverloadException
	 * 			If the storageunit is full
	 */			
	public PayCoin(Coin coin) throws DisabledException, OverloadException {
		this.coin = coin;
		
		BigDecimal nickel = new BigDecimal(0.05);
		BigDecimal dime = new BigDecimal(0.10);
		BigDecimal quarter = new BigDecimal(0.25);
		BigDecimal loonie = new BigDecimal(1.00);
		BigDecimal toonie = new BigDecimal(2.00);
		List<BigDecimal> denomination = null;
		denomination.add(nickel);
		denomination.add(dime);
		denomination.add(quarter);
		denomination.add(loonie);
		denomination.add(toonie);
		
		Currency cur = Currency.getInstance(Locale.CANADA);
		
		CoinValidator valid = new CoinValidator(cur, denomination);
		UnidirectionalChannel<Coin> rejectionSink = new UnidirectionalChannel<Coin>(valid);
		UnidirectionalChannel<Coin> storageSink = new UnidirectionalChannel<Coin>(valid);
		
		valid.connect(rejectionSink, storageSink);
		valid.accept(coin);
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
		capacity.accept(coin);
	}
}

}
