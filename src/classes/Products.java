/**
 * 
 */
package classes;

/**
 * @author luisgzz
 *
 */
public class Products {

	private String Name;
	private String totalCost;
	private String Price;
	private String Stock;

	public Products() {
	
		
	}
		
	public Products(String Name,String totalCost,String Price, String Stock) {
	
			this.Name = Name;
			this.totalCost = totalCost;
			this.Price = Price;
			this.Stock = Stock;
	
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	
	/**
	 * @return the cost
	 */

	/**
	 * @return the price
	 */
	public String getPrice() {
		return Price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		Price = price;
	}

	/**
	 * @return the stock
	 */
	public String getStock() {
		return Stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(String stock) {
		Stock = stock;
	}

	/**
	 * @return the totalCost
	 */
	public String getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
}
