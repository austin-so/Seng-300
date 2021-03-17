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
	
	private List<BigDecimal> denomination;
	private CoinStorageUnit capacity = new CoinStorageUnit(500);
	private Coin coin = new Coin(BigDecimal.TEN, Currency.getInstance(Locale.CANADA));

	/**
	 * 
	 * @param coin
	 * 			Allows the user to pay for their items with coins
	 *
	 */
	
	public PayCoin(Coin coin) {
		this.coin = coin;
		setDenomination();
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
	
	public void validateCoin() throws DisabledException {
		
		CoinValidator valid = new CoinValidator(Currency.getInstance(Locale.CANADA), denomination);
		UnidirectionalChannel<Coin> rejectionSink = new UnidirectionalChannel<Coin>(valid);
		UnidirectionalChannel<Coin> storageSink = new UnidirectionalChannel<Coin>(valid);
		
		valid.connect(rejectionSink, storageSink);
		valid.accept(coin);
	}
	
	public void setDenomination() {
		List<BigDecimal> denomination = null;
		BigDecimal nickel = new BigDecimal(0.05);
		BigDecimal dime = new BigDecimal(0.10);
		BigDecimal quarter = new BigDecimal(0.25);
		BigDecimal loonie = new BigDecimal(1.00);
		BigDecimal toonie = new BigDecimal(2.00);
		
		denomination.add(nickel);
		denomination.add(dime);
		denomination.add(quarter);
		denomination.add(loonie);
		denomination.add(toonie);
		this.denomination = denomination;
	}
	
}


