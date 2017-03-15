package inventoryPrac;

public class StoreManager {

	private StoreCapable storageCapable;

	public void addStorage(StoreCapable storage) {
		this.storageCapable = storage;
	}

	public void addCDProduct(String name, int price, int numOfTracks) {
		storageCapable.storeCDProduct(name, price, numOfTracks);
	}

	public void addBookProduct(String name, int price, int pageSize) {
		storageCapable.storeBookProduct(name, price, pageSize);
	}

	public String listProducts() {

		String productsNames = "";

		for (Product product : storageCapable.getAllProduct()) {
			productsNames += product.name + "\n";
		}

		return productsNames;

	}

	public int getTotalProductPrice() {

		int totalPrice = 0;

		for (Product product : storageCapable.getAllProduct()) {
			totalPrice += product.price;
		}

		return totalPrice;
	}

}
