package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.*;

import com.google.gson.Gson;
public class RestaurantAccount {
	private String id;
	private String password;
	private String email;
	private String myRTname;
	private Restaurant myRestrant;
	public RestaurantAccount(String id, String password,String email, String myRTname) {
		super();
		this.id = id;
		this.password = password;
		this.email=email;
		this.myRTname = myRTname;
	}
	public RestaurantAccount(String id, String password,String email, String myRTname,Restaurant rt) {
		super();
		this.id = id;
		this.password = password;
		this.email=email;
		this.myRTname = myRTname;
		this.myRestrant = rt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMyRTname() {
		return myRTname;
	}
	public void setMyRTname(String myRTname) {
		this.myRTname = myRTname;
	}
	public Restaurant getMyRestrant() {
		return myRestrant;
	}
	public void setMyRestrant(Restaurant myRestrant) {
		this.myRestrant = myRestrant;
	}
	/**
	 * 把某個restaurantAccount 存到資料庫
	 * store Class restaurantAccount into SQL
	 * @param jdbcUrl
	 */
	public void storeToDB(String jdbcUrl) {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl);

			//obj Restaurant conver into String
			String name = this.myRTname;
			String id   = this.id;
			String password = this.password;
			String email = this.email;
			String jsonRestrant = new Gson().toJson(this.myRestrant,Restaurant.class);
			// store it to SQL
			//                              tablename     ??name
			Statement statement = con.createStatement();

			statement.executeUpdate("INSERT INTO RT_Account (name,id,password,email,jsonRT)\r\n" + "VALUES ('"+name+"','" +id+ "','"+password+"','"+email+"','"+jsonRestrant+"');\n");
			statement.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database when storeToDB in RestaurantAccount");
			e.printStackTrace();
		}
	}
	/**
	 * 某個餐廳改變後，allrt要變
	 * DB也要變
	 */
	public static ArrayList<RestaurantAccount> updateRTList(String jdbcUrl,ArrayList<RestaurantAccount> allrt,RestaurantAccount changed){
		ArrayList<RestaurantAccount> toreturn= new ArrayList<RestaurantAccount>();
		for (int i=0;i<allrt.size();i++) {
			RestaurantAccount R = allrt.get(i);
			if(R.myRTname==changed.myRTname) {
				toreturn.add(changed); 
			}else {
				toreturn.add(allrt.get(i));
			}
		}
		try {
			Connection con = DriverManager.getConnection(jdbcUrl);
			String SQLDelete = "UPDATE RT_Account SET jsonRT = ? WHERE name = ?";
			PreparedStatement ps = con.prepareStatement(SQLDelete);
			ps.setString(1, new Gson().toJson(changed.myRestrant,Restaurant.class));
			ps.setString(2, changed.myRTname);
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database  rt.storeToDB");
			e.printStackTrace();
		}
		return toreturn;
	}


	@Override
	public String toString() {
		return "RestaurantAccount [id=" + id + ", password=" + password + ", email=" + email
				+ ", myRTname=" + myRTname + ", myRestrant=" + myRestrant + "]";
	}
	/**
	 * 從資料庫裏面 抓出全部的餐廳帳戶
	 * get All RestaurantAccount from SQL
	 * @param jdbcUrl
	 * @return
	 */
	public static ArrayList<RestaurantAccount> getDB(String jdbcUrl) {

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
//				System.out.println(jsonRestrant);
//				System.out.println("break");
//				System.out.println(new JSONObject(jsonRestrant));
				Restaurant rt = Restaurant.FromJsontoObject(new JSONObject(jsonRestrant));
				allAccount.add(new RestaurantAccount(id,password,email,name,rt));
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
	/**
	 * update訂單狀態
	 */

	/**
	 * 抓出餐廳是自己的order
	 * &沒有外送員接單
	 */
	public ArrayList<Order> RT_LookingForOrder(ArrayList<Order> allOrder){
		ArrayList<Order> toreturn = new ArrayList<Order>();
		for(Order O:allOrder) {
			if(this.myRTname.equals(O.supplier.getName())&&O.drivername.equals("none")&&O.state.equals("等待商家確認")) {
				toreturn.add(O);
			}
		}
		return toreturn;
	}

	/**
	 * 查看訂單歷史狀態
	 * 抓出餐廳是自己的order
	 * &有外送員的訂單
	 */
	public ArrayList<Order> RT_OrderHistory(ArrayList<Order> allOrder){
		ArrayList<Order> toreturn = new ArrayList<Order>();
		for(Order O:allOrder) {
			if(this.myRTname.equals(O.supplier.getName())) {
				toreturn.add(O);
			}
		}
		return toreturn;
	}

	/**
	 * 是否要接單接下某張單
	 * 把狀態那一欄改掉
	 * 回傳某張單，同時更改DB中的訂單
	 */	
	public Order Accept(Order O,ArrayList<Order> allOrder){
		O.state="餐點製作中";
		Order.updateOrderList("jdbc:sqlite:Orderdb.db",allOrder,O);
		return O;
	}
	

}
