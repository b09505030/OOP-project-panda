import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Order {
	public String 		auto;
	public String 		state;
	public ShopCart 	shopCart;
	public double 		totalprice;
	public long		bookingtime_ms;
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
	
	public Order(String auto, String state, ShopCart shopCart, double totalprice, long bookingtime_ms, long donetime_ms,
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
		return "Order [\nauto=" + auto + "\n, state=" + state + "\n, shopCart=" + shopCart + "\n, totalprice=" + totalprice
				+ "\n, bookingtime_ms=" + bookingtime_ms + "\n, donetime_ms=" + donetime_ms + "\n, destination=" + destination
				+ "\n, buyername=" + buyername + "\n, drivername=" + drivername + "\n, supplier=" + supplier + "]";
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
				String 		shopCart		=new Gson().toJson(this.shopCart);
				double 		totalprice		=this.totalprice;
				long		bookingtime_ms	=this.bookingtime_ms;
				long 		donetime_ms		=this.donetime_ms;
				String 		destination		=new Gson().toJson(this.destination);
				String 		buyername		=this.buyername;
				String 		drivername		=this.drivername;
				String 		supplier		=new Gson().toJson(this.supplier);
//				System.out.println(state+"'\n,'" +shopCart+ "'\n,'"+totalprice+"'\n,'"+bookingtime_ms+"'\n,'"+donetime_ms+"'\n,'"+destination+"'\n,'"+buyername+"'\n,'"+drivername+"'\n,'"+supplier);
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
					String 		auto=result.getString("auto");
					String 		state=result.getString("state");
					ShopCart 	shopCart=new Gson().fromJson(result.getString("shopCart"),ShopCart.class) ;
					double 		totalprice=result.getDouble("totalprice");
					long		bookingtime_ms=result.getLong("bookingtime_ms");
					long 		donetime_ms=result.getLong("donetime_ms");
					Position 	destination=new Gson().fromJson(result.getString("destination"),Position.class);
					String 		buyername=result.getString("buyername");
					String 		drivername=result.getString("drivername");
					Restaurant 	supplier=new Gson().fromJson(result.getString("supplier"), Restaurant.class);
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
	
	
}
