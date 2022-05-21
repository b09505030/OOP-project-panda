import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Restaurant {
	private String 					name;
	private Position 				position;
	private String 					phone;
	private String 					store_description;
	private String 					order_description;
	private ArrayList<String> 		type;
	private ArrayList<MenuItem> 	menu;
	private Business_Time 			business_time;
	

	public class Position {
		public String address;
		public float latitude,longitude;
		public Position(String address, float latitude, float longitude) {
			super();
			this.address = address;
			this.latitude = latitude;
			this.longitude = longitude;
		}
		@Override
		public String toString() {
			return "Position [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStore_description() {
		return store_description;
	}

	public void setStore_description(String store_description) {
		this.store_description = store_description;
	}

	public String getOrder_description() {
		return order_description;
	}

	public void setOrder_description(String order_description) {
		this.order_description = order_description;
	}

	public ArrayList<String> getType() {
		return type;
	}

	public void setType(ArrayList<String> type) {
		this.type = type;
	}

	public ArrayList<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<MenuItem> menu) {
		this.menu = menu;
	}

	public Business_Time getBusiness_time() {
		return business_time;
	}

	public void setBusiness_time(Business_Time business_time) {
		this.business_time = business_time;
	}

	
	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", position=" + position + ", phone=" + phone + ", store_description="
				+ store_description + ", order_description=" + order_description + ", type=" + type + ", menu=" + menu
				+ ", business_time=" + business_time 
				+ "]";
		}
	
	
	

	
}
