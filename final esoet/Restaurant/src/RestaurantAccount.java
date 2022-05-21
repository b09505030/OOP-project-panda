import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;

public class RestaurantAccount {
	private String id;
	private String password;
	private String email;
	private float discount;
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
	//store Class restaurant into SQL
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
				//                              tablename     ����name
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
			return "RestaurantAccount [id=" + id + ", password=" + password + ", email=" + email + ", discount="
					+ discount + ", myRTname=" + myRTname + ", myRestrant=" + myRestrant + "]";
		}
		
	
}
