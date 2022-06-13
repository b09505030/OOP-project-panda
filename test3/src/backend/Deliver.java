package backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Deliver {
	private String id;
	private String password;
	private String phone;
	private String email;
	private String name;
	public Deliver(String id, String password, String phone, String email, String name) {
		super();
		this.id = id;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Deliver [id=" + id + ", password=" + password + ", phone=" + phone + ", email=" + email + ", name="
				+ name + "]";
	}
	public void storeToDB(String jdbcUrl) {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl);

			//obj Restaurant convert into String
			String id=this.id;
			String password=this.password;
			String phone=this.phone;
			String email=this.email;
			String name=this.name;
			// store it to SQL
			//                              table name     項目name
			Statement statement = con.createStatement();
			statement.executeUpdate("INSERT INTO Deliver (name,id,password,email,phone)\r\n" + "VALUES ('"+name+"','" +id+ "','"+password+"','"+email+"','"+phone+"');\n");
			statement.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database when storeToDB in Deliver");
			e.printStackTrace();
		}
	}
	/**
	 * print all deliver data in SQLite
	 * @param jdbcUrl path of database
	 *
	 */
	public static void getDB(String jdbcUrl) {
		//String  jdbcUrl = "jdbc:sqlite:/D:\\esoeoop\\repo\\uber esoet\\Client\\clientdb.db";
		try
		{
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT * FROM Deliver";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String id=result.getString("id");
				String password=result.getString("password");
				String phone=result.getString("phone");
				String email=result.getString("email");
				String name=result.getString("name");
				System.out.println("Deliver [id=" + id + ", password=" + password + ", phone=" + phone + ", email=" + email + ", name="
						+ name + "]");
			}
			connection.close();
			statement.close();
			result.close();

		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}


	}


	/**
	 * 接單 在外送員那邊填上自己的名字
	 * 代表接單
	 * 同時更改DB中的定單
	 */
	public Order Accept(Order O,ArrayList<Order> allOrder){
		O.state="餐點運送中";
		O.drivername=this.name;
		Order.updateOrderList("jdbc:sqlite:Orderdb.db",allOrder,O);
		return O;
	}
	/**
	 * 找出可以接的單子
	 * 要有店家同意過
	 * 然後外送員那邊沒有名字
	 */
	public ArrayList<Order> DE_LookingForOrder(ArrayList<Order> allOrder){
		ArrayList<Order> toreturn = new ArrayList<Order>();
		for(Order O:allOrder) {
			if(O.state.equals("餐點製作中")&&O.drivername.equals("none")) {
				toreturn.add(O);
			}
		}
		return toreturn;
	}
	/**
	 * 找出可以完成的
	 * 要有店家同意過
	 * 然後外送員那邊沒有名字
	 */
	public ArrayList<Order> DeliveringOrder(ArrayList<Order> allOrder){
		ArrayList<Order> toreturn = new ArrayList<Order>();
		for(Order O:allOrder) {
			if(O.state.equals("餐點運送中")&&O.drivername.equals(this.name)) {
				toreturn.add(O);
			}
		}
		return toreturn;
	}
	
	/**
	 * 把狀態改成完成
	 * 並記錄時間
	 * 代表完成訂單
	 * 同時更改DB中的定單
	 */
	public Order Done(Order O,ArrayList<Order> allOrder){
		O.state="餐點抵達";
		long millis = System.currentTimeMillis();
		O.donetime_ms= millis;
		java.sql.Time donetime = new java.sql.Time(millis);
		System.out.println(donetime);
		Order.updateOrderList("jdbc:sqlite:Orderdb.db",allOrder,O);
		return O;
	}
	/**
	 * 抓出所有自己的名字的訂單
	 * @return
	 */
	public ArrayList<Order> history(ArrayList<Order> allOrder){
		ArrayList<Order> sb = new ArrayList<Order>();
		for (Order O:allOrder) {
			if(O.drivername.equals(this.name)) {
				sb.add(O);
			}
		}
		return sb;
	}

	
}
