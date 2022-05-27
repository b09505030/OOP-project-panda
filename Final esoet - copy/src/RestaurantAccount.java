import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
				String jsonRestrant = new Gson().toJson(this.myRestrant);
				// store it to SQL
				//                              tablename     項目name
				Statement statement = con.createStatement();
				
				statement.executeUpdate("INSERT INTO RT_Account (name,id,password,email,jsonRT)\r\n" + "VALUES ('"+name+"','" +id+ "','"+password+"','"+email+"','"+jsonRestrant+"');\n");
				statement.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Error connecting to SQLite database when storeToDB in RestaurantAccount");
				e.printStackTrace();
			}
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
		/**
		 * update訂單狀態
		 */
		
		/**
		 * 抓出餐廳是自己的order
		 * &沒有外送員接單
		 */
		
		/**
		 * 查看訂單歷史狀態
		 * 抓出餐廳是自己的order
		 * &有外送員的訂單
		 */
		
		/**
		 * 是否要接單
		 * 把狀態那一欄改掉
		 */
	
}
