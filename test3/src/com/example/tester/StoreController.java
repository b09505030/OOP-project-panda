package com.example.tester;

import java.util.ArrayList;

import backend.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class StoreController {
	@FXML
	private Label info1;
	@FXML
	private Label info2;
	@FXML
	private Label info3;
	@FXML
	private Label info4;
	@FXML
	private TextField req;
	@FXML
	private TextField pricecut;
	
	RestaurantAccount MyAccount;
	ArrayList<Order> WaitACC;
	@FXML
	protected void searchStoreDetailonclick() throws Exception {
//	System.out.println("searchStoreDetailonclick");
	Restaurant[] Allrt = storage.ALLRT;
	MyAccount = new RestaurantAccount("Iddd", "password", "email@g.com", "雞の專家_劍潭");
	MyAccount.setMyRestrant(Restaurant.GetARestaurantFromArray(Allrt, MyAccount.getMyRTname()));
	storage.RT_ACCOUNT=MyAccount;
	Restaurant R=MyAccount.getMyRestrant();
	StringBuffer toreturn=new StringBuffer();
	toreturn.append("餐廳: " + R.getName() + "\n地址: " + R.getPosition().address + "\n電話: " + R.getPhone() + "\n商家描述: "
			+ R.getStore_description() + "\n點餐描述: \n" + R.getOrder_description() + "\n食物類別: \n" + R.getType()); 
	toreturn.append("\n營業時間: \n一"+ R.getBusiness_time().mon.start+"~"+R.getBusiness_time().mon.end+" 二"+R.getBusiness_time().tue.start+"~"+R.getBusiness_time().tue.end+" \n三"+R.getBusiness_time().wed.start+"~"+R.getBusiness_time().wed.end+" \n四"+R.getBusiness_time().thu.start+"~"+R.getBusiness_time().thu.end+" \n五"+R.getBusiness_time().fri.start+"~"+R.getBusiness_time().fri.end+" 六"+R.getBusiness_time().sat.start+"~"+R.getBusiness_time().sat.end+" \n日"+R.getBusiness_time().sun.start+"~"+R.getBusiness_time().sun.end+" ");
	if(R.getPriceForDiscount()!=0.0&&R.getDiscount()!=0.0) {
		toreturn.append("折扣: 滿"+R.getPriceForDiscount()+"打"+R.getDiscount()*10+"折\n");
	}
	info1.setText(toreturn.toString());
	}
	@FXML
	protected void searchIteamDetailonclick() throws Exception {
		Restaurant[] Allrt = Restaurant.Fromjson_detailtoArray();
		RestaurantAccount MyAccount = new RestaurantAccount("Iddd", "password", "email@g.com", "雞の專家_劍潭");
		MyAccount.setMyRestrant(Restaurant.GetARestaurantFromArray(Allrt, MyAccount.getMyRTname()));
		Restaurant R=MyAccount.getMyRestrant();
		StringBuffer toreturn = new StringBuffer();
		toreturn.append("食物類別\n"+R.getType()+"\n");
		for(menuItem i:R.getMenu()) {
			toreturn.append(i.name+" "+i.price+"\n");
		}
		info2.setText(toreturn.toString());
	}
	 
	@FXML
	protected void historyonclick() throws Exception {
		ArrayList<Order> Myhis = MyAccount.RT_OrderHistory(storage.ALLORDER);
		StringBuffer toreturn = new StringBuffer();
		for(Order O:Myhis) {
			toreturn.append(O.toString()+"\n");
		}
		info3.setText(toreturn.toString());
	}
	@FXML
	protected void neworder() throws Exception {
		storage.ALLORDER = Order.getDB("jdbc:sqlite:Orderdb.db");
		WaitACC = MyAccount.RT_LookingForOrder(storage.ALLORDER);
		StringBuffer sb = new StringBuffer();
		for (Order O:WaitACC) {
			sb.append(O.toString());
		};
		info4.setText(sb.toString());
		
	}
	@FXML
	protected void acceptonclick() throws Exception {
		if(WaitACC.size()!=0)MyAccount.Accept(WaitACC.get(0), storage.ALLORDER);
		
	}
	
	@FXML
	protected void applyCupon() throws Exception {
		Double Price = Double.parseDouble(req.getText());
		Double Discount= Double.parseDouble(pricecut.getText());
		Restaurant rt = MyAccount.getMyRestrant();
		rt.setDiscount(Price, Discount/10.0);
		System.out.println(rt);
		MyAccount.setMyRestrant(rt);
		System.out.println(MyAccount);
		storage.ALLRT_ACCOUNT = RestaurantAccount.updateRTList("jdbc:sqlite:userdb.db", storage.ALLRT_ACCOUNT, MyAccount);
		
	}
	@FXML
	protected void closeonClick() throws Exception {
		System.out.println("cup");
	}

}
