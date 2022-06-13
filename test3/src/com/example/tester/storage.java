package com.example.tester;
import java.util.ArrayList;

import backend.*;
public class storage {
	public static int i=-1;
	public static Position DESTINATION=new Position("0",0,0);
	public static ShopCart CURRENTCART=new ShopCart("0",false,DESTINATION,null);
	
	public static Client CLIENT;
	public static RestaurantAccount RT_ACCOUNT;
	public static Deliver DELIVER;
	
	public static Restaurant[] ALLRT=Restaurant.Fromjson_detailtoArray();
	public static ArrayList<RestaurantAccount> ALLRT_ACCOUNT=RestaurantAccount.getDB("jdbc:sqlite:userdb.db");
	public static ArrayList<Order> ALLORDER=Order.getDB("jdbc:sqlite:Orderdb.db");
}
