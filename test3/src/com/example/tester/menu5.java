package com.example.tester;

import java.util.ArrayList;

import backend.Order;
import backend.Restaurant;
import backend.RestaurantAccount;
import backend.ShopCart;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.*;

public class menu5 {
	@FXML
    private Label menu1;
	@FXML
    private Label info;
	@FXML
    private TextField food1;
	@FXML
    private TextField food2;
	@FXML
    private TextField food3;
	@FXML
    private TextField food4;
	@FXML
    private TextField food5;
	Restaurant[] allrt= Restaurant.Fromjson_detailtoArray();
	ArrayList<RestaurantAccount> RTList= RestaurantAccount.getDB("jdbc:sqlite:userdb.db");
	@FXML
	 public void sure() throws Exception
	 {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}int i = storage.i;
		storage.CURRENTCART = new ShopCart(storage.CLIENT.getName(),storage.CLIENT.getVIP(),storage.DESTINATION,allrt[i]);
		storage.CURRENTCART.addfood(allrt[i].getMenu().get(0).name,Integer. parseInt(food1.getText()) );
		storage.CURRENTCART.addfood(allrt[i].getMenu().get(1).name,Integer. parseInt(food2.getText()) );
		storage.CURRENTCART.addfood(allrt[i].getMenu().get(2).name,Integer. parseInt(food3.getText()) );
		storage.CURRENTCART.addfood(allrt[i].getMenu().get(3).name,Integer. parseInt(food4.getText()) );
		storage.CURRENTCART.addfood(allrt[i].getMenu().get(4).name,Integer. parseInt(food5.getText()) );
//		System.out.println(storage.CURRENTCART);
		Order o = new Order(storage.CURRENTCART);
		o.storeToDB("jdbc:sqlite:Orderdb.db");
		Parent root=FXMLLoader.load(getClass().getResource("acc.fxml"));
        Stage window=(Stage)menu1.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	 protected void back() throws Exception 
	 {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}Parent root=FXMLLoader.load(getClass().getResource("resselect.fxml"));
        Stage window=(Stage)menu1.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	 }
	
	 @FXML
	 public void initialize() 
	 {	storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}int i=storage.i;
		 double dx,dy;
		dx = (storage.DESTINATION.latitude-allrt[i].getPosition().latitude)*111;
		dy = (storage.DESTINATION.longitude-allrt[i].getPosition().longitude)*100;
		float time  = 30 + Math.round(1.6*Math.sqrt(dx*dx+dy*dy));
		String dis = "滿"+allrt[i].getPriceForDiscount()+"打"+allrt[i].getDiscount()+"折";
		 if(allrt[i].getDiscount()==0||allrt[i].getPriceForDiscount()!=0)dis="無";
		 info.setText("預計運送時間:"+(int)time+"分鐘 優惠:"+dis);
		String tmp="";
		for(int id=0;id<allrt[i].getMenu().size();id++)
		{
		   tmp+=allrt[i].getMenu().get(id).name;
		   tmp+=allrt[i].getMenu().get(id).price+"\n";
		}
		menu1.setText(tmp);
	 }	
}