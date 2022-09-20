/**
 * 
 */
package classes;

/**
 * @author luisgzz
 *
 */
public class Stock {
	private String productName;
	private String ogStock;
	private String stockRemaining;
	
	
	public Stock() {
		
	}
	
	
	public Stock(String productName, String ogStock, String stockRemaining) {
		this.productName = productName;
		this.ogStock = ogStock;
		this.stockRemaining = stockRemaining;
		
		
		
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
	 * @return the ogStock
	 */
	public String getOgStock() {
		return ogStock;
	}


	/**
	 * @param ogStock the ogStock to set
	 */
	public void setOgStock(String ogStock) {
		this.ogStock = ogStock;
	}


	/**
	 * @return the stockRemaining
	 */
	public String getStockRemaining() {
		return stockRemaining;
	}


	/**
	 * @param stockRemaining the stockRemaining to set
	 */
	public void setStockRemaining(String stockRemaining) {
		this.stockRemaining = stockRemaining;
	}
	
	
}
