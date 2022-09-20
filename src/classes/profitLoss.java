/**
 * 
 */
package classes;

/**
 * @author luisgzz
 *
 */
public class profitLoss {
	private String productName;
	private String Earnings;
	private String Capital;
	private String profits;
	
	
	public profitLoss() {
		
	}

	public profitLoss(String productName, String Earnings, String Capital, String profits) {
		
		this.productName = productName;
		this.Earnings = Earnings;
		this.Capital = Capital;
		this.profits = profits;
		
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the earnings
	 */
	public String getEarnings() {
		return Earnings;
	}

	/**
	 * @param earnings the earnings to set
	 */
	public void setEarnings(String earnings) {
		Earnings = earnings;
	}

	/**
	 * @return the capital
	 */
	public String getCapital() {
		return Capital;
	}

	/**
	 * @param capital the capital to set
	 */
	public void setCapital(String capital) {
		Capital = capital;
	}

	/**
	 * @return the profits
	 */
	public String getProfits() {
		return profits;
	}

	/**
	 * @param profits the profits to set
	 */
	public void setProfits(String profits) {
		this.profits = profits;
	}
}



