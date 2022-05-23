import java.util.ArrayList;

public class TestRestaurant {
	public static void main(String[] args) {
//		//onlyfortesting
//		Restaurant.FromGsontoObject();
//		//onlyfortesting
//		Restaurant.FromGsonFiletoObject();

		
		// To Get All restaurant in json_detail
		System.out.println("Get All restaurant in json_detail ");
		// 這個Allrt 就是所有餐廳的資料
		Restaurant[] Allrt = Restaurant.Fromjson_detailtoArray();
		System.out.println("印三個");
		for (int i = 0; i < 3; i++) {
			System.out.println(Allrt[i]);
		}

		
		// Get a Restaurant from Array
		//這邊是展示如何取得餐廳的各種資料
		System.out.println("\n查看商家資訊");
		System.out.println("\n查看商品資訊");
		Restaurant B = Restaurant.GetARestaurantFromArray(Allrt, "春來麵線");
		System.out.println(B.getName());
		System.out.println(B.getPosition().address);
		System.out.println(B.getPosition().latitude);
		System.out.println(B.getPosition().longitude);
		System.out.println(B.getPhone());
		System.out.println(B.getStore_description());
		for (String s : B.getType()) {
			System.out.println(s);
		}
		for (MenuItem s : B.getMenu()) {
			System.out.println(s.name);
			System.out.println(s.price);
		}
		System.out.println(B.getBusiness_time().mon.start);
		System.out.println(B.getBusiness_time().mon.end);
		System.out.println(B.getBusiness_time().tue.start);
		System.out.println(B.getBusiness_time().tue.end);
		System.out.println(B.getBusiness_time().wed.start);
		System.out.println(B.getBusiness_time().wed.end);
		System.out.println(B.getBusiness_time().thu.start);
		System.out.println(B.getBusiness_time().thu.end);
		System.out.println(B.getBusiness_time().fri.start);
		System.out.println(B.getBusiness_time().fri.end);
		System.out.println(B.getBusiness_time().sat.start);
		System.out.println(B.getBusiness_time().sat.end);
		System.out.println(B.getBusiness_time().sun.start);
		System.out.println(B.getBusiness_time().sun.end);

		
		// Get all rtname
		System.out.println("\nget String[] rt_name ");
		//就只是覺得把名字抓出來比較方便用
		String[] rt_name = new String[Allrt.length];
		for (int i = 0; i < Allrt.length; i++) {
			rt_name[i] = Allrt[i].getName();
		}
		for (String s : rt_name) {
			System.out.println(s);
		}
//
//		
		// Create My Account
		System.out.println("\nCreate My Account");
		RestaurantAccount MyAccount = new RestaurantAccount("Iddd", "password", "email@g.com", "雞の專家_劍潭");
		MyAccount.setMyRestrant(Restaurant.GetARestaurantFromArray(Allrt, MyAccount.getMyRTname()));
		System.out.println(MyAccount);

		// store a restrant to SQL
		String jdbcUrl = "jdbc:sqlite:userdb.db";
		MyAccount.storeToDB(jdbcUrl);
		RestaurantAccount.getDB(jdbcUrl);
		Client.getDB(jdbcUrl);
		Deliver.getDB(jdbcUrl);
		
		System.out.println("\nfrom SQL to object: ");
		ArrayList<RestaurantAccount> RT_AccountfromDB = RestaurantAccount.getDB(jdbcUrl);
		for (RestaurantAccount e :RT_AccountfromDB) {
			System.out.println(e);
		}

	}

	

	
	

	
	

}
