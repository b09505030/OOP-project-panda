package backend;

import org.json.JSONObject;

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
			JSONObject tmp = new JSONObject();
			tmp.put("name", name);
			tmp.put("price", price);
			return tmp.toString();
		}
		
		
	}
