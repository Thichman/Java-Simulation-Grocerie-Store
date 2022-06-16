
public class StoreDriver {

	public static void main(String[] args) {
		GroceryStore ingles = new GroceryStore(5);
		ingles.run(200, .25, 1, 30);
		ingles.printData();

	}

}
