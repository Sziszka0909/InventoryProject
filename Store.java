package inventoryPrac;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Store implements StoreCapable {

	String fileName = "src/inventoryPrac/products.xml";
	ArrayList<Product> products = new ArrayList<Product>();

	@Override
	public void storeCDProduct(String name, int price, int numOfTracks) {
		createProduct("CD", name, price, numOfTracks);

	}

	@Override
	public void storeBookProduct(String name, int price, int pageSize) {
		createProduct("Book", name, price, pageSize);
	}

	protected Product createProduct(String type, String name, int price, int size) {
		if (type == "CD") {
			Product product = new CDProduct(name, price, size);
			store(product);
			return product;
		} else {
			Product product = new BookProduct(name, price, size);
			store(product);
			return product;
		}
	}

	public void store(Product product) {
		saveToXML(product);
		storeProduct(product);
	}

	protected abstract void storeProduct(Product product);

	private void saveToXML(Product product) {
		File file = new File(fileName);
		try {
			DocumentBuilderFactory dBFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dBFact.newDocumentBuilder();
			Document doc;
			Element rootElement;

			if (file.exists()) {
				doc = dBuilder.parse(fileName);
				doc.normalize();
				rootElement = doc.getDocumentElement();
			} else {
				doc = dBuilder.newDocument();
				rootElement = doc.createElement("Products");
				doc.appendChild(rootElement);
			}

			Element prod = doc.createElement("Product");
			rootElement.appendChild(prod);

			Attr attrName = doc.createAttribute("name");
			attrName.setValue(product.name);
			prod.setAttributeNode(attrName);

			Attr attrPrice = doc.createAttribute("price");
			attrPrice.setValue(product.price.toString());
			prod.setAttributeNode(attrPrice);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileName));

			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Product> getAllProduct() {
		return products;
	}

	public ArrayList<Product> loadProducts() {
		File file = new File(fileName);

		try {
			DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dBFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.normalize();

			NodeList nodeList = doc.getElementsByTagName("Product");
			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node node = nodeList.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String elementName = element.getAttribute("Name");
					int elementPrice = Integer.parseInt(element.getAttribute("Price"));
					products.add(createProduct("CD", elementName, elementPrice, 10));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

}
