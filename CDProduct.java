package inventoryPrac;

public class CDProduct extends Product {

	public int numOfTracks;

	public CDProduct(String name, Integer price, int numOfTracks) {
		super(name, price);
		this.numOfTracks = numOfTracks;
	}

	@Override
	public String toString() {

		return "Cím: " + name + "\nÁr: " + price + "\nSzámok: " + numOfTracks;
	}

}
