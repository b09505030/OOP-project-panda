package backend;

import java.io.BufferedReader;
import java.io.FileReader;
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
				+ ", business_time=" + business_time + "]";
	}

	/**
	 * convert a json to a Restaurant object this method is only for testing
	 * 把一個String 轉成 object
	 * 測試用 實際用不到
	 */
	public static void FromGsontoObject() {
		// test one restaurant from gson to Restaurant object
		String gsonRestrant = "{\r\n" + "        \"name\": \"雞の專家_劍潭\",\r\n" + "        \"position\": {\r\n"
				+ "            \"address\": \"台北市士林區文林路110巷9號\",\r\n" + "            \"latitude\": 25.088,\r\n"
				+ "            \"longitude\": 121.527\r\n" + "        },\r\n" + "        \"phone\": \"0000000\",\r\n"
				+ "        \"store_description\": \"\",\r\n"
				+ "        \"order_description\": \"1.大量訂購,請提早2日預約2.雞排一律不切\",\r\n" + "        \"type\": [\r\n"
				+ "            \"日式\",\r\n" + "            \"其他\",\r\n" + "            \"小吃\",\r\n"
				+ "            \"中式\",\r\n" + "            \"西式\"\r\n" + "        ],\r\n" + "        \"menu\": [\r\n"
				+ "            {\r\n" + "                \"name\": \"雞排\",\r\n"
				+ "                \"price\": \"辣 70, 不辣 70\"\r\n" + "            },\r\n" + "            {\r\n"
				+ "                \"name\": \"雞翅\",\r\n" + "                \"price\": \"30\"\r\n"
				+ "            },\r\n" + "            {\r\n" + "                \"name\": \"無骨雞塊(限量)\",\r\n"
				+ "                \"price\": \"70\"\r\n" + "            },\r\n" + "            {\r\n"
				+ "                \"name\": \"薯條\",\r\n" + "                \"price\": \"40\"\r\n"
				+ "            }\r\n" + "        ],\r\n" + "        \"business_time\": {\r\n"
				+ "            \"mon\": {\r\n" + "                \"start\": \"10:00\",\r\n"
				+ "                \"end\": \"16:30\"\r\n" + "            },\r\n" + "            \"tue\": {\r\n"
				+ "                \"start\": \"10:00\",\r\n" + "                \"end\": \"16:30\"\r\n"
				+ "            },\r\n" + "            \"wed\": {\r\n" + "                \"start\": \"10:00\",\r\n"
				+ "                \"end\": \"16:30\"\r\n" + "            },\r\n" + "            \"thu\": {\r\n"
				+ "                \"start\": \"10:00\",\r\n" + "                \"end\": \"16:30\"\r\n"
				+ "            },\r\n" + "            \"fri\": {\r\n" + "                \"start\": \"10:00\",\r\n"
				+ "                \"end\": \"16:30\"\r\n" + "            },\r\n" + "            \"sat\": {\r\n"
				+ "                \"start\": \"10:00\",\r\n" + "                \"end\": \"16:30\"\r\n"
				+ "            },\r\n" + "            \"sun\": {\r\n" + "                \"start\": \"10:00\",\r\n"
				+ "                \"end\": \"16:30\"\r\n" + "            }\r\n" + "        }\r\n" + "    }\r\n"
				+ "\r\n" + "";
		Restaurant resrantA = new Gson().fromJson(gsonRestrant, Restaurant.class);
		// object
		System.out.println("test to String():" + resrantA);
		// json
		System.out.println("print json" + gsonRestrant);

	}
	/**
	 * 測試file input output 實際上用不到
	 * FROM A .json FILE convert a json to a Restaurant object this method is only
	 * for testing
	 */
	public static void FromGsonFiletoObject() {
		StringBuffer stingbufer = new StringBuffer();
		try {
			// WRITE TO A FILE
			// BufferedWriter bw = new BufferedWriter(new FileWriter("Aresrant.json"));
			// bw.close();
			BufferedReader br = new BufferedReader(new FileReader("Aresrant.json"));
			String s;
			while ((s = br.readLine()) != null) {
				stingbufer.append(s + "\n");
				// System.out.println(s);
			}
			// System.out.println(stingbufer);
			br.close();

		} catch (Exception ex) {
			return;
		}
		String gsonRestrant = stingbufer.toString();
		Restaurant resrantA = new Gson().fromJson(gsonRestrant, Restaurant.class);
		// object
		System.out.println("TEST to String():\n" + resrantA);
		// json
		System.out.println("print json\n" + gsonRestrant);

	}
	
	/**
	 * 從Array裡面 利用餐廳名字 抓出這個餐廳的物件
	 * Get A Restaurant object From Array
	 * @param Allrt Restaurant[]
	 * @param findwhat Restaurant name that you want to get
	 * @return
	 */
	public static Restaurant GetARestaurantFromArray(Restaurant[] Allrt, String findwhat) {
		Restaurant A = null;
		for (Restaurant rt : Allrt) {
			if (findwhat.equals(rt.getName())) {
				A = rt;
			}
		}
		return A;
	}
	
	
	/**
	 * To Get every restuarant in json_detail Return a ArrayList
	 * 取得json裡面全部的資料
	 * @return AllRestrant ArrayList<Restaurant>
	 */
	public static Restaurant[]  Fromjson_detailtoArray() {
		StringBuffer stingbufer = new StringBuffer();
		try {
			// WRITE TO A FILE
			// BufferedWriter bw = new BufferedWriter(new FileWriter("Aresrant.json"));
			// bw.close();
			BufferedReader br = new BufferedReader(new FileReader("stores_detail.json"));
			String s;
			while ((s = br.readLine()) != null) {
				stingbufer.append(s + "\n");
				// System.out.println(s);
			}
			// System.out.println(stingbufer);
			br.close();

		} catch (Exception ex) {
			System.out.println("Error occered at Fromgson_detailtoList()");
			return null;
		}
		String gsonRestrant = stingbufer.toString();
		Restaurant[] Allrt = new Gson().fromJson(gsonRestrant, Restaurant[].class);
//		for (Restaurant r : Allrt) {
//			System.out.println(r);
//		}
		return Allrt;

	}

	
	
	

	
}
