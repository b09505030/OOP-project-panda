import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.google.gson.Gson;

public class TestRestaurant {
	public static void main(String[] args) {
		// onlyfortesting
		// FromGsontoObject();
		// onlyfortesting
		// FromGsonFiletoObject();

		
//		// To Get All restaurant in json_detail
//		System.out.println("Get All restaurant in json_detail ");
//		// 這個Allrt 就是所有餐廳的資料
//		Restaurant[] Allrt = Fromjson_detailtoArray();
//		System.out.println("印三個");
//		for (int i = 0; i < 3; i++) {
//			System.out.println(Allrt[i]);
//		}
//
//		
//		// Get a Restaurant from Array
//		//這邊是展示如何取得餐廳的各種資料
//		System.out.println("\n查看商家資訊");
//		System.out.println("\n查看商品資訊");
//		Restaurant B = GetARestaurantFromArray(Allrt, "春來麵線");
//		System.out.println(B.getName());
//		System.out.println(B.getPosition().address);
//		System.out.println(B.getPosition().latitude);
//		System.out.println(B.getPosition().longitude);
//		System.out.println(B.getPhone());
//		System.out.println(B.getStore_description());
//		for (String s : B.getType()) {
//			System.out.println(s);
//		}
//		for (MenuItem s : B.getMenu()) {
//			System.out.println(s.name);
//			System.out.println(s.price);
//		}
//		System.out.println(B.getBusiness_time().mon.start);
//		System.out.println(B.getBusiness_time().mon.end);
//		System.out.println(B.getBusiness_time().tue.start);
//		System.out.println(B.getBusiness_time().tue.end);
//		System.out.println(B.getBusiness_time().wed.start);
//		System.out.println(B.getBusiness_time().wed.end);
//		System.out.println(B.getBusiness_time().thu.start);
//		System.out.println(B.getBusiness_time().thu.end);
//		System.out.println(B.getBusiness_time().fri.start);
//		System.out.println(B.getBusiness_time().fri.end);
//		System.out.println(B.getBusiness_time().sat.start);
//		System.out.println(B.getBusiness_time().sat.end);
//		System.out.println(B.getBusiness_time().sun.start);
//		System.out.println(B.getBusiness_time().sun.end);
//
//		
//		// Get all rtname
//		System.out.println("\nget String[] rt_name ");
//		//就只是覺得把名字抓出來比較方便用
//		String[] rt_name = new String[Allrt.length];
//		for (int i = 0; i < Allrt.length; i++) {
//			rt_name[i] = Allrt[i].getName();
//		}
//		for (String s : rt_name) {
//			System.out.println(s);
//		}
//
//		
//		// Create My Account
//		System.out.println("\nCreate My Account");
//		RestaurantAccount MyAccount = new RestaurantAccount("Iddd", "password", "email@g.com", "雞の專家_劍潭");
//		MyAccount.setMyRestrant(GetARestaurantFromArray(Allrt, MyAccount.getMyRTname()));
//		System.out.println(MyAccount);
//
//		// store a restrant to SQL
//		String jdbcUrl = "jdbc:sqlite:RT_Accountdb.db";
//		MyAccount.storeToDB(jdbcUrl);
//		
//		
//		System.out.println("\nfrom SQL to object: ");
//		ArrayList<RestaurantAccount> RT_AccountfromDB = getDB(jdbcUrl);
//		for (RestaurantAccount e :RT_AccountfromDB) {
//			System.out.println(e);
//		}

	}

	private static Restaurant GetARestaurantFromArray(Restaurant[] Allrt, String findwhat) {
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
	 * 
	 * @return AllRestrant ArrayList<Restaurant>
	 */
	private static Restaurant[] Fromjson_detailtoArray() {
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

	/**
	 * convert a json to a Restaurant object this method is only for testing
	 */
	private static void FromGsontoObject() {
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
	 * FROM A .json FILE convert a json to a Restaurant object this method is only
	 * for testing
	 */
	private static void FromGsonFiletoObject() {
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

	private static ArrayList<RestaurantAccount> getDB(String jdbcUrl) {

		ArrayList<RestaurantAccount> allAccount = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT * FROM RT_Account";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				String name = result.getString("name");
				String id   = result.getString("id");
				String password = result.getString("password");
				String email = result.getString("email");
				String jsonRestrant = result.getString("jsonRT");
				Restaurant rt = new Gson().fromJson(jsonRestrant, Restaurant.class);
				allAccount.add(new RestaurantAccount(name,id,password,email,rt));
			}
			connection.close();
			statement.close();
			result.close();

		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}
		return allAccount;
	}

}
