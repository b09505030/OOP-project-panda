package backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONObject;

public class Order {
	private int	 		auto;
	public String 		state;
	public ShopCart 	shopCart;
	public double 		totalprice;
	public long			bookingtime_ms;
	public long 		donetime_ms;
	public Position 	destination;
	public String 		buyername;
	public String 		drivername;
	public Restaurant 	supplier;
	//接收購物車的建構子
	public Order(ShopCart shopCart) {
		super();
		this.state = "等待商家確認";
		this.shopCart = shopCart;
		this.totalprice = shopCart.getTotalprice();
		long millis = System.currentTimeMillis();
		this.bookingtime_ms = millis;
		this.donetime_ms = 0;
		java.sql.Date NOW = new java.sql.Date(millis);
		java.sql.Time now = new java.sql.Time(millis);
		System.out.println("建立訂單時間"+NOW+" "+now);
		this.destination = shopCart.destination;
		this.buyername = shopCart.buyer;
		this.drivername = "none";
		this.supplier = shopCart.rt;
	}
	//copy constructor
	public Order(Order in) {
		this.auto=in.auto;
		this.state = in.state;
		this.shopCart = in.shopCart;
		this.totalprice = in.totalprice;
		this.bookingtime_ms = in.bookingtime_ms;
		this.donetime_ms = in.donetime_ms;
		this.destination = in.destination;
		this.buyername = in.buyername;
		this.drivername = in.drivername;
		this.supplier = in.supplier;
	}
	//全部資料的建構子
	public Order(int auto, String state, ShopCart shopCart, double totalprice, long bookingtime_ms, long donetime_ms,
				 Position destination, String buyername, String drivername, Restaurant supplier) {
		super();
		this.auto = auto;
		this.state = state;
		this.shopCart = shopCart;
		this.totalprice = totalprice;
		this.bookingtime_ms = bookingtime_ms;
		this.donetime_ms = donetime_ms;
		this.destination = destination;
		this.buyername = buyername;
		this.drivername = drivername;
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "訂單編號= " + auto + "\n訂單狀態= " + state + "\n總金額=" + totalprice
				+ "\n訂餐時間=" + new java.sql.Time(bookingtime_ms) + "\n送達時間=" + new java.sql.Time(donetime_ms) + "\n目的=" + destination.address
				+ "\n購買人=" + buyername + "\n外送員=" + drivername + "\n商家="+supplier.getName();
	}
	/**
	 * 把某個Order 存到資料庫
	 * store Class restaurantAccount into SQL
	 * @param jdbcUrl
	 */
	public void storeToDB(String jdbcUrl) {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl);

			String 		state			=this.state;
			String 		shopCart		=this.shopCart.toString();
			double 		totalprice		=this.totalprice;
			long		bookingtime_ms	=this.bookingtime_ms;
			long 		donetime_ms		=this.donetime_ms;
			String 		destination		=this.destination.toString();
			String 		buyername		=this.buyername;
			String 		drivername		=this.drivername;
			String 		supplier		=this.supplier.tojsonStr();
//			System.out.println(state+"'\n,'" +shopCart+ "'\n,'"+totalprice+"'\n,'"+bookingtime_ms+"'\n,'"+donetime_ms+"'\n,'"+destination+"'\n,'"+buyername+"'\n,'"+drivername+"'\n,'"+supplier);
			String prSQL = "INSERT INTO ordertable(state,shopCart,totalprice,bookingtime_ms,donetime_ms,destination,buyername,drivername,supplier)"
					+ "VALUES (?,?,?,?,?,?,?,?,?);";
			PreparedStatement PRST = con.prepareStatement(prSQL);
			PRST.setString(1, state);
			PRST.setString(2, shopCart);
			PRST.setDouble(3, totalprice);
			PRST.setLong(4, bookingtime_ms);
			PRST.setLong(5, donetime_ms);
			PRST.setString(6,destination);
			PRST.setString(7,buyername);
			PRST.setString(8,drivername);
			PRST.setString(9,supplier);
			int resul = PRST.executeUpdate();
			PRST.clearParameters();
			if(resul==1)System.out.println("success");
			PRST.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database  Order.storeToDB");
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Order> getDB(String jdbcUrl) {

		ArrayList<Order> allOrder = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT * FROM ordertable";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				int 		auto=result.getInt("auto");
				String 		state=result.getString("state");
				ShopCart 	shopCart = ShopCart.FromJsontoObject(new JSONObject((result.getString("shopCart")))) ;
				double 		totalprice=result.getDouble("totalprice");
				long		bookingtime_ms=result.getLong("bookingtime_ms");
				long 		donetime_ms=result.getLong("donetime_ms");
				Position 	destination=Position.FromJsontoObject(new JSONObject(result.getString("destination")));
				String 		buyername=result.getString("buyername");
				String 		drivername=result.getString("drivername");
				Restaurant 	supplier=Restaurant.FromJsontoObject(new JSONObject(result.getString("supplier")));
				allOrder.add(new Order(auto,state,shopCart,totalprice,bookingtime_ms,donetime_ms,destination,buyername,drivername,supplier));
			}
			connection.close();
			statement.close();
			result.close();

		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}
		return allOrder;
	}
	/**
	 * User oderID to get a Order
	 * @param allOrder ArrayList that has every Order
	 * @param OrderID 訂單編號
	 * @return
	 */
	public static Order getOrderfromArrayList(ArrayList<Order> allOrder,long OrderID) {
		Order toreturn=null;
		for(Order O:allOrder) {
			if(O.auto==OrderID) {
//				System.out.println(O);
				toreturn=O;
			}
		}
		return new Order(toreturn);
	};
	/**
	 * 某個訂單的改變後，allOrder要變
	 * DB也要變
	 * 
	 */
	public static ArrayList<Order> updateOrderList(String jdbcUrl,ArrayList<Order> allOrder,Order changed){
		ArrayList<Order> toreturn= new ArrayList<Order>();
		for (int i=0;i<allOrder.size();i++) {
			Order O = allOrder.get(i);
			if(O.auto==changed.auto) {
				toreturn.add(changed); 
			}else {
				toreturn.add(allOrder.get(i));
			}
		}
		try {
			Connection con = DriverManager.getConnection(jdbcUrl);
			String SQLDelete = "DELETE FROM ordertable WHERE auto=?";
			PreparedStatement prSQLDelete = con.prepareStatement(SQLDelete);
			prSQLDelete.setInt(1,changed.auto);
			int deletsuccese = prSQLDelete.executeUpdate();
			if(deletsuccese==1)System.out.println("success to delete data");
			int 		auto			=changed.auto;
			String 		state			=changed.state;
			String 		shopCart		=changed.shopCart.toString();
			double 		totalprice		=changed.totalprice;
			long		bookingtime_ms	=changed.bookingtime_ms;
			long 		donetime_ms		=changed.donetime_ms;
			String 		destination		=changed.destination.toString();
			String 		buyername		=changed.buyername;
			String 		drivername		=changed.drivername;
			String 		supplier		=changed.supplier.tojsonStr();
//				System.out.println(state+"'\n,'" +shopCart+ "'\n,'"+totalprice+"'\n,'"+bookingtime_ms+"'\n,'"+donetime_ms+"'\n,'"+destination+"'\n,'"+buyername+"'\n,'"+drivername+"'\n,'"+supplier);
			String prSQL = "INSERT INTO ordertable(state,shopCart,totalprice,bookingtime_ms,donetime_ms,destination,buyername,drivername,supplier,auto)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement PRST = con.prepareStatement(prSQL);
			PRST.setString(1, state);
			PRST.setString(2, shopCart);
			PRST.setDouble(3, totalprice);
			PRST.setLong(4, bookingtime_ms);
			PRST.setLong(5, donetime_ms);
			PRST.setString(6,destination);
			PRST.setString(7,buyername);
			PRST.setString(8,drivername);
			PRST.setString(9,supplier);
			PRST.setInt(10,auto);
			int resul = PRST.executeUpdate();
			PRST.clearParameters();
			if(resul==1)System.out.println("success");
			PRST.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database  Order.storeToDB");
			e.printStackTrace();
		}
		return toreturn;
	}


}
