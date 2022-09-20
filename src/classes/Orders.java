/**
 * 
 */
package classes;

/**
 * @author luisgzz
 *
 */
public class Orders {
	private String productName;
	private String customerName;
	private String dateOfOrder;
	private String price;
	private String quantity;

	public Orders() {
		
	}
	
	public Orders(String productName, String customerName, String dateOfOrder, String price, String quantity) {
		this.productName = productName;
		this.customerName = customerName;
		this.dateOfOrder = dateOfOrder;
		this.price = price;
		this.quantity = quantity;
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
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the dateOfOrder
	 */
	public String getDateOfOrder() {
		return dateOfOrder;
	}

	/**
	 * @param dateOfOrder the dateOfOrder to set
	 */
	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
}

	
