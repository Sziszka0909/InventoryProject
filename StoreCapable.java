package inventoryPrac;

import java.util.ArrayList;

public interface StoreCapable {

	public void storeCDProduct(String name, int price, int numOfTracks);

	public void storeBookProduct(String name, int price, int pageSize);

	public ArrayList<Product> getAllProduct();

}
