package inventoryPrac;

public class Main {

	public static void main(String[] args) {
		StoreManager storeManager = new StoreManager();
		StoreCapable storeCapable = new PersistentStore();
		storeManager.addStorage(storeCapable);
		storeManager.addCDProduct("Janika énekel", 1500, 12);
		storeManager.addBookProduct("Kaka Matyi meséi", 10000, 3);
		System.out.println(storeManager.listProducts());
		System.out.println(storeManager.getTotalProductPrice());
	}

}
