/**
 * class that store food menu
 * with data name and price
 * @author b09505028
 * 
 *
 */
public class MenuItem{
		public String name;
		public String price;
		//overload
		public MenuItem(String name, String price) {
			super();
			this.name = name;
			this.price = price;
		}
		@Override
		public String toString() {
			return "MenuItem [name=" + name + ", price=" + price + "]";
		}
		
		
	}
