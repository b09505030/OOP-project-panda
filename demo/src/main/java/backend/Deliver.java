package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			//                              table name     ����name
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
	
	public String accept(){
		return null;
	}
	public String done(){
		return null;
	}
	public String history(){
		return null;
	}
	
}
