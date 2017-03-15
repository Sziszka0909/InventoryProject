package inventoryPrac;

public class BookProduct extends Product {

	public int pageSize;

	public BookProduct(String name, Integer price, int pageSize) {
		super(name, price);
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {

		return "Cím: " + name + "\nÁr: " + price + "\nOldalak száma: " + pageSize;
	}

}
