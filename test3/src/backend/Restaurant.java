package backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Restaurant {
	private String 					name;
	private Position 				position;
	private String 					phone;
	private String 					store_description;
	private String 					order_description;
	private ArrayList<String> 		type;
	private ArrayList<menuItem> 	menu;
	private Business_Time 			business_time;
	private double 					priceForDiscount;
	private double 					discount;

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

	public ArrayList<menuItem> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<menuItem> menu) {
		this.menu = menu;
	}

	public Business_Time getBusiness_time() {
		return business_time;
	}

	public void setBusiness_time(Business_Time business_time) {
		this.business_time = business_time;
	}

	public Restaurant(String name, Position position, String phone, String store_description, String order_description, ArrayList<String> type, ArrayList<menuItem> menu, Business_Time business_time) {
		this.name = name;
		this.position = position;
		this.phone = phone;
		this.store_description = store_description;
		this.order_description = order_description;
		this.type = type;
		this.menu = menu;
		this.business_time = business_time;
	}

	public Restaurant(String name, Position position, String phone, String store_description, String order_description, ArrayList<String> type, ArrayList<menuItem> menu, Business_Time business_time, double priceForDiscount, double discount) {
		this.name = name;
		this.position = position;
		this.phone = phone;
		this.store_description = store_description;
		this.order_description = order_description;
		this.type = type;
		this.menu = menu;
		this.business_time = business_time;
		this.priceForDiscount = priceForDiscount;
		this.discount = discount;
	}

	public double getPriceForDiscount() {
		return priceForDiscount;
	}

	public double getDiscount() {
		return discount;
	}

	/**
	 * 利用這個函數，設定滿額折扣
	 * @param priceForDiscount
	 * @param discount
	 */
	public void setDiscount(double priceForDiscount,double discount) {
		this.priceForDiscount = priceForDiscount;
		this.discount = discount;
	}	
	

	/**
	 * convert a json to a Restaurant object this method is only for testing
	 * 把一個String 轉成 Restaurant object discount is excluded
	 */
	public static Restaurant FromJsontoObject_z(JSONObject input) {
		// test one restaurant from gson to Restaurant object
//		JSONObject rt = new JSONObject(gsonRestrant);
		JSONObject rt = input;
		String 					name=rt.getString("name");
		Position 				position=new Position(rt.getJSONObject("position").getString("address"),rt.getJSONObject("position").getDouble("latitude"),rt.getJSONObject("position").getDouble("longitude"));
		String 					phone=rt.getString("phone");
		String 					store_description=rt.getString("store_description");
		String 					order_description=rt.getString("order_description");
		ArrayList<String> 		type=new ArrayList<String>();
		for (int i=0;i<rt.getJSONArray("type").length();i++){
			type.add(rt.getJSONArray("type").getString(i));
		}
		ArrayList<menuItem> 	menu=new ArrayList<menuItem>();
		for (int i=0;i<rt.getJSONArray("menu").length();i++){
			menuItem tmp = new menuItem(rt.getJSONArray("menu").getJSONObject(i).getString("name"),rt.getJSONArray("menu").getJSONObject(i).getString("price"));
			menu.add(tmp);
		}
		Business_Time 			business_time;
		{
			Business_Time.BusDay mon = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("mon").getString("start"),rt.getJSONObject("business_time").getJSONObject("mon").getString("end"));
			Business_Time.BusDay tue = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("tue").getString("start"),rt.getJSONObject("business_time").getJSONObject("tue").getString("end"));
			Business_Time.BusDay wed = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("wed").getString("start"),rt.getJSONObject("business_time").getJSONObject("wed").getString("end"));
			Business_Time.BusDay thu = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("thu").getString("start"),rt.getJSONObject("business_time").getJSONObject("thu").getString("end"));
			Business_Time.BusDay fri = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("fri").getString("start"),rt.getJSONObject("business_time").getJSONObject("fri").getString("end"));
			Business_Time.BusDay sat = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("sat").getString("start"),rt.getJSONObject("business_time").getJSONObject("sat").getString("end"));
			Business_Time.BusDay sun = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("sun").getString("start"),rt.getJSONObject("business_time").getJSONObject("sun").getString("end"));
			business_time=new Business_Time(mon,tue,wed,thu,fri,sat,sun);
		}
//		System.out.println(name);
//		System.out.println(position);
//		System.out.println(phone);
//		System.out.println(store_description);
//		System.out.println(order_description);
//		System.out.println(type);
//		System.out.println(menu);
//		System.out.println(business_time);
		Restaurant A = new Restaurant(name,position,phone,store_description,order_description,type,menu,business_time);
		return A;


	}
	public Restaurant[] getArrayFromAccount(ArrayList<RestaurantAccount> allacount) {
		Restaurant[] toreturn = new Restaurant[allacount.size()];
		for(int i=0;i<allacount.size();i++) {
			toreturn[i]=allacount.get(i).getMyRestrant();
		}
		return toreturn;
	}
	
	@Override
	public String toString() {
		StringBuffer toreturn=new StringBuffer();
		toreturn.append("餐廳: " + name + "\n地址: " + position.address + "\n電話: " + phone + "\n商家描述: "
				+ store_description + "\n點餐描述: " + order_description + "\n食物類別: " + type + "\n菜單: \n");
		for(menuItem i:menu) {
			toreturn.append("   "+i.name+" "+i.price+"$\n");
		}
		toreturn.append("營業時間: 一"+business_time.mon.start+"~"+business_time.mon.end+" 二"+business_time.tue.start+"~"+business_time.tue.end+" 三"+business_time.wed.start+"~"+business_time.wed.end+" 四"+business_time.thu.start+"~"+business_time.thu.end+" 五"+business_time.fri.start+"~"+business_time.fri.end+" 六"+business_time.sat.start+"~"+business_time.sat.end+" 日"+business_time.sun.start+"~"+business_time.sun.end+" ");
		if(priceForDiscount!=0.0&&discount!=0.0) {
			toreturn.append("折扣: 滿"+priceForDiscount+"打"+discount*10+"折\n");
		}
		return toreturn.toString();
	}
	public String toString(Position P) {
		StringBuffer toreturn=new StringBuffer();
		toreturn.append("餐廳: " + name+"\n");
		double dx,dy,delivetime;
		dx = (this.position.latitude-P.latitude)*111;
		dy = (this.position.longitude-P.longitude)*100;
		delivetime  = 25+5*(dx*dx+dy*dy);
		toreturn.append("外送時間: "+delivetime);
		toreturn.append("\n地址: " + position.address + "\n電話: " + phone + "\n商家描述: "
				+ store_description + "\n點餐描述: " + order_description + "\n食物類別: " + type + "\n菜單: \n");
		for(menuItem i:menu) {
			toreturn.append("   "+i.name+" "+i.price+"$\n");
		}
		toreturn.append("營業時間: 一"+business_time.mon.start+"~"+business_time.mon.end+" 二"+business_time.tue.start+"~"+business_time.tue.end+" 三"+business_time.wed.start+"~"+business_time.wed.end+" 四"+business_time.thu.start+"~"+business_time.thu.end+" 五"+business_time.fri.start+"~"+business_time.fri.end+" 六"+business_time.sat.start+"~"+business_time.sat.end+" 日"+business_time.sun.start+"~"+business_time.sun.end+" ");
		if(priceForDiscount!=0.0&&discount!=0.0) {
			toreturn.append("折扣: 滿"+priceForDiscount+"打"+discount*10+"折\n");
		}
		return toreturn.toString();
	}

	/**
	 * convert a json to a Restaurant object this method is only for testing
	 * 把一個String 轉成 Restaurant object discount is included
	 */
	public static Restaurant FromJsontoObject(JSONObject input) {
		JSONObject rt = input;
		String 					name=rt.getString("name");
		Position 				position=new Position(rt.getJSONObject("position").getString("address"),rt.getJSONObject("position").getDouble("latitude"),rt.getJSONObject("position").getDouble("longitude"));
		String 					phone=rt.getString("phone");
		String 					store_description=rt.getString("store_description");
		String 					order_description=rt.getString("order_description");
		ArrayList<String> 		type=new ArrayList<String>();
		for (int i=0;i<rt.getJSONArray("type").length();i++){
			type.add(rt.getJSONArray("type").getString(i));
		}
		ArrayList<menuItem> 	menu=new ArrayList<menuItem>();
		for (int i=0;i<rt.getJSONArray("menu").length();i++){
			menuItem tmp = new menuItem(rt.getJSONArray("menu").getJSONObject(i).getString("name"),rt.getJSONArray("menu").getJSONObject(i).getString("price"));
			menu.add(tmp);
		}
		Business_Time 			business_time;
		{
			Business_Time.BusDay mon = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("mon").getString("start"),rt.getJSONObject("business_time").getJSONObject("mon").getString("end"));
			Business_Time.BusDay tue = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("tue").getString("start"),rt.getJSONObject("business_time").getJSONObject("tue").getString("end"));
			Business_Time.BusDay wed = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("wed").getString("start"),rt.getJSONObject("business_time").getJSONObject("wed").getString("end"));
			Business_Time.BusDay thu = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("thu").getString("start"),rt.getJSONObject("business_time").getJSONObject("thu").getString("end"));
			Business_Time.BusDay fri = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("fri").getString("start"),rt.getJSONObject("business_time").getJSONObject("fri").getString("end"));
			Business_Time.BusDay sat = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("sat").getString("start"),rt.getJSONObject("business_time").getJSONObject("sat").getString("end"));
			Business_Time.BusDay sun = new Business_Time.BusDay(rt.getJSONObject("business_time").getJSONObject("sun").getString("start"),rt.getJSONObject("business_time").getJSONObject("sun").getString("end"));
			business_time=new Business_Time(mon,tue,wed,thu,fri,sat,sun);
		}
		double 					priceForDiscount=rt.getDouble("priceForDiscount");
		double 					discount=rt.getDouble("discount");
//		System.out.println(name);
//		System.out.println(position);
//		System.out.println(phone);
//		System.out.println(store_description);
//		System.out.println(order_description);
//		System.out.println(type);
//		System.out.println(menu);
//		System.out.println(business_time);
		Restaurant A = new Restaurant(name,position,phone,store_description,order_description,type,menu,business_time,priceForDiscount,discount);
		return A;


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
	public static Restaurant[] Fromjson_detailtoArray() {
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
		JSONArray josnArray = new JSONArray(gsonRestrant);
		Restaurant[] Allrt = new Restaurant[josnArray.length()];
		for (int i=0;i<josnArray.length();i++) {
			Allrt[i]=Restaurant.FromJsontoObject_z(josnArray.getJSONObject(i));
		}
		return Allrt;

	}
	public String tojsonStr(){
		JSONObject jsonRestrant = new JSONObject(this);
//		System.out.println(jsonRestrant);
		
//		System.out.println(new JSONObject(this.getBusiness_time().toString()));
		jsonRestrant.put("business_time", new JSONObject(this.getBusiness_time().toString()));
//		System.out.println(jsonRestrant);
		
//		System.out.println(new JSONArray(this.getMenu().toString()));
		jsonRestrant.put("menu", new JSONArray(this.getMenu().toString()));
//		System.out.println(jsonRestrant);

//		System.out.println(new JSONObject(this.getPosition().toString()));
		jsonRestrant.put("position", new JSONObject(this.getPosition().toString()));
//		System.out.println(jsonRestrant);
//		System.out.println(Restaurant.FromJsontoObject(jsonRestrant));
		return jsonRestrant.toString();
		
	}
	public String visualizeDate(Restaurant rt) {
		return ""; 
	}
	






}
