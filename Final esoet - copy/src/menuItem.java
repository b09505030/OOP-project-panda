/**
 * class that store food menu
 * with data name and price
 * @author b09505028
 * 
 *
 */
public class menuItem{
		public String name;
		public String price;
		//overload
		public menuItem(String name, String price) {
			super();
			this.name = name;
			this.price = price;
		}
		@Override
		public String toString() {
			return "[name=" + name + ", price=" + price + "]";
		}
		
		
	}
