package inventoryPrac;

public class PersistentStore extends Store {

	@Override
	public void storeProduct(Product product) {
		products.add(product);
	}

}
