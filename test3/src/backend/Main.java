package backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.json.JSONObject;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		// To Get All restaurant in json_detail
		// 這個Allrt 就是所有餐廳的資料
		Restaurant[] Allrt = Restaurant.Fromjson_detailtoArray();
//		System.out.println(Allrt[0]);
//		System.out.println(Allrt[1]);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Allrt.json"));
    		for (int i = 0;i<Allrt.length; i++) {
    			RestaurantAccount A=new RestaurantAccount("0","0","0",Allrt[i].getName(),Allrt[i]);
    			A.storeToDB("jdbc:sqlite:userdb.db");
    			bw.write(new Gson().toJson(new Gson().toJson(A,RestaurantAccount.class))+"\n");
    			System.out.println(new Gson().toJson(A,RestaurantAccount.class));
    		}
    		bw.close();
    		} catch (Exception ex) {
    			System.out.println("Error occered at Write to currentclient");
    		}
		String  jdbcUrl = "jdbc:sqlite:userdb.db";
		ArrayList<RestaurantAccount> AllrtAccount = RestaurantAccount.getDB(jdbcUrl);
		for(RestaurantAccount R:AllrtAccount) {
			System.out.println(R);
		}
		
		
		
		//生成購物車 要準備一個使用者的 名字  地址  VIP  
		String   username = "謝祥祥";
		boolean  VIP  = false;
		Position plc = new Position("106台北市大安區長興街31號",25.016731, 121.545355);
		Restaurant rtt= Restaurant.GetARestaurantFromArray(Allrt, "樸食");
		//這邊我手動幫餐廳設定滿額折購  但這應該要讓店家自己設定
		rtt.setDiscount(200,0.9);
//		System.out.println("001"+rtt);
//		System.out.println("002"+plc);
		//當客戶逛餐廳時 點進去 選菜介面 的瞬間 用餐廳的名字建立一個currentCart如下
		ShopCart currentCart;
		//舉個例子  假設我選餐廳 春來麵線
		currentCart = new ShopCart(username,VIP,plc, Restaurant.GetARestaurantFromArray(Allrt, "春來麵線"));
		//不要這間餐廳就直接選其他餐廳
		//比如選 樸食
		currentCart = null;
		currentCart = new ShopCart(username,VIP,plc,rtt);

//		System.out.println(currentCart);
		//好ㄟ可以點餐了
		System.out.println("點餐 還要運費");
		currentCart.addfood("宮保雞丁", 1);
		System.out.println("加  滿180 VIP免運");
		currentCart.addfood("香煎雞腿", 1);
		System.out.println("加  滿300打折");
		currentCart.addfood("梅花豬", 2);
		System.out.println(currentCart+"\n");
		//不要梅花豬好了
		System.out.println("取消");
		currentCart.removefood("梅花豬");
		//在來一點雞腿好了
		System.out.println("加點\n");
		currentCart.addfood("香煎雞腿", 2);
		System.out.println("\n在來一點雞腿"+currentCart+"\n\n");
		//送出購物車
		Order o = new Order(currentCart);
//		JSONObject tst = new JSONObject(currentCart.toString());
//		System.out.println(tst);
//		ShopCart A = ShopCart.FromJsontoObject(tst);
		
		
		
		
		
		String jdbcUrlOrder = "jdbc:sqlite:Orderdb.db";
//		o.storeToDB(jdbcUrlOrder);
//		System.out.println(currentCart);
		//System.out.println(o);
		System.out.println("\n\n\n\n\n\n");
		ArrayList<Order> AllOrder = Order.getDB(jdbcUrlOrder);
		for(Order O:AllOrder) {
			System.out.println(O);
		}
		Order particularOrder = Order.getOrderfromArrayList(AllOrder, 37);
		particularOrder.drivername="某個外宋元";
		System.out.println(particularOrder);
		ArrayList<Order> newall = Order.updateOrderList(jdbcUrlOrder, AllOrder, particularOrder);
		for(Order O:newall) {
			System.out.println(O);
		}
		
		


	}

}
