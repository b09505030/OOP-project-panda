package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Main2Ordertesting {
	public static void main(String[] args) {
		
		Client MyAccount = new Client("Billy","b09505028","b09505028","0988233936","b09505028@gmail.com");
		
		ArrayList<RestaurantAccount> AllrtAccount = RestaurantAccount.getDB("jdbc:sqlite:userdb.db");
		Restaurant[] Allrt= new Restaurant[AllrtAccount.size()];
		for (int i=0;i<AllrtAccount.size();i++) {
			Allrt[i]=AllrtAccount.get(i).getMyRestrant();
		}
		RestaurantAccount RTAccount = new RestaurantAccount("Iddd", "password", "email@g.com", "雞の專家_劍潭");
		RTAccount.setMyRestrant(Restaurant.GetARestaurantFromArray(Allrt, RTAccount.getMyRTname()));
		Restaurant supplier = Allrt[0];
		
		Deliver DeliverAccount = new Deliver("b09505030","b09505030","0988","b09505030@gmail.com","張");
		
		Position destination=new Position("0",0,0);
		try {
		BufferedReader br = new BufferedReader(new FileReader("destination.json"));
		destination = new Gson().fromJson(br.readLine(),Position.class);
		br.close();
		}catch (Exception ex) {
			System.out.println("Error occured at get desination()");
		}
//		System.out.println(MyAccount);
//		System.out.println(RTAccount);
//		System.out.println(DeliverAccount);
//		System.out.println(destination);
		
		
		
		ShopCart currentCart;
		currentCart = new ShopCart(MyAccount.getName(),MyAccount.getVIP(),destination,supplier);
//		System.out.println(currentCart);
		
		String OrderJDBC = "jdbc:sqlite:Orderdb.db";
//		Order o = new Order(currentCart);
//		o.storeToDB("jdbc:sqlite:Orderdb.db");
//		System.out.println(o);
		
		ArrayList<Order> AllOrder=  Order.getDB(OrderJDBC);
//		for (Order O:Order.getDB(OrderJDBC)) {
//			System.out.println(O);
//		};
		
//		
//		AllOrder=  Order.getDB(OrderJDBC);
//		ArrayList<Order> WaitACC = RTAccount.RT_LookingForOrder(AllOrder);
//		for (Order O:WaitACC) {
//			System.out.println(O);
//		};
//		if(WaitACC.size()!=0)RTAccount.Accept(WaitACC.get(0), AllOrder);
//		
//		
//		
//		AllOrder=  Order.getDB(OrderJDBC);
//		ArrayList<Order> WaitDLV = DeliverAccount.DE_LookingForOrder(AllOrder);
//		for (Order O:WaitDLV) {
//			System.out.println(O);
//		};
//		if(WaitDLV.size()!=0)DeliverAccount.Accept(WaitDLV.get(0), AllOrder);
//		
//		AllOrder=  Order.getDB(OrderJDBC);
//		ArrayList<Order>Waitdone = DeliverAccount.DeliveringOrder(AllOrder);
//		if(Waitdone.size()!=0)DeliverAccount.Done(Waitdone.get(0), AllOrder);
		Restaurant rt = RTAccount.getMyRestrant();
		rt.setDiscount(300, 0.8);
		System.out.println(rt);
		RTAccount.setMyRestrant(rt);
		System.out.println(MyAccount);
//		MyAccount.storeToDB("jdbc:sqlite:userdb.db");
		AllrtAccount = RestaurantAccount.updateRTList("jdbc:sqlite:userdb.db", AllrtAccount, RTAccount);
		
		
	}
}
