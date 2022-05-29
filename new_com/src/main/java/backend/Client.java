package backend;

import java.text.DateFormat;
import java.sql.*;

/**
 * Class for client with data Name,Id,Password,Phone,Email VIP Due(if pay for
 * VIP)
 * 
 * with constructor Client(String Name,String Id,String Password,String
 * Phone,String Email)
 * 
 * with method MonthVIP() get VIP for a month storeToDB(String jdbcUrl) store
 * client data into SQLite
 * 
 */
public class Client {
	private String Name, Id, Password, Phone, Email;
	private boolean VIP;
	private Date Due;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean isVIP() {
		return VIP;
	}

	public void setVIP(boolean vIP) {
		VIP = vIP;
	}

	public Date getDue() {
		return Due;
	}

	public void setDue(Date due) {
		Due = due;
	}

	/**
	 * �إ� �|����� �w�]VIP false ������� create member data set VIP false. birth to current
	 * data store data to userdb.db
	 */
	Client(String Name, String Id, String Password, String Phone, String Email) {
		this.Name = Name;
		this.Id = Id;
		this.Password = Password;
		this.Phone = Phone;
		this.Email = Email;
		this.VIP = false;

	}

	@Override
	public String toString() {
		if (VIP) {
			return "Client [Name=" + Name + ", Id=" + Id + ", Password=" + Password + ", Phone=" + Phone + ", Email="
					+ Email + ", VIP=" + VIP + ", Due=" + Due + "]";
		}
		return "Client [Name=" + Name + ", Id=" + Id + ", Password=" + Password + ", Phone=" + Phone + ", Email="
				+ Email + ", VIP=" + VIP + "]";
	}

	/**
	 * �}�l�q�\�@�Ӥ몺VIP VIP = true �]30��(30*1000*60*60*24�L��)�ᬰDue
	 * 
	 * Start VIP for a month Due was set after 30days(30*1000*60*60*24 milesec)
	 * 
	 */
	public void MonthVIP() {
		long millis = System.currentTimeMillis();
		Date Now = new Date(millis);
		Date tmp = new Date(millis);
		tmp.setTime(millis + 30L * 1000 * 60 * 60 * 24);
		Due = tmp;
		VIP = true;

		DateFormat currentDate = DateFormat.getDateInstance();
		System.out.println(currentDate.format(Now));
		System.out.println(currentDate.format(Due));

	}
	

	/**
	 * Store a Client object to database
	 * @param jdbcUrl
	 */
	public void storeToDB(String jdbcUrl) {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl);
			/**
			 * �O SQL �y�k ���W��� Insertion is SQL term INSERT INTO client (Name,Id , Password,
			 * Phone, Email, VIP,Due) VALUES ('txt', 'txt', 'txt', 'txt', 'txt',
			 * boolean,int);
			 */
			String insertion = "INSERT INTO client (Name,Id , Password, Phone, Email, VIP,Due)\r\n" + "VALUES ('" + Name
					+ "', '" + Id + "', '" + Password + "', '" + Phone + "', '" + Email + "', " + VIP + "," + Due.getTime()
					+ ");\n";
			Statement statement = con.createStatement();
			statement.executeUpdate(insertion);
			statement.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database when storeToDB");
			e.printStackTrace();
		}
	}
	/**
	 * print all Client data in SQLite
	 * @param jdbcUrl path of database
	 * 
	 */
		public static void getDB(String jdbcUrl) {
			//String  jdbcUrl = "jdbc:sqlite:/D:\\esoeoop\\repo\\uber esoet\\Client\\clientdb.db";
			try 
			{
				Connection connection = DriverManager.getConnection(jdbcUrl);
				String sql = "SELECT * FROM client";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while (result.next()) {
					String Name = result.getString("Name");
					String Id = result.getString("ID");
					String Email = result.getString("Email");
					String Password = result.getString("Password");
					String Phone = result.getString("Phone");
					boolean VIP = result.getBoolean("VIP");
					long milsDue = result.getLong("Due");
					Date Due = new Date(milsDue);
					System.out.println("Client [Name=" + Name + 
							", Id=" + Id + 
							", Password=" + Password + 
							", Phone=" + Phone + 
							", Email=" + Email + 
							", VIP=" + VIP  + 
							", Due=" + Due +"]"
							) ;
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
		 * �H����d���\�U
		 */
		
		/**
		 * update�ۤv���q�檬�A
		 */
		
		/**
		 * �d�ݥثe�q�檬�A
		 * ��XState�٨S�e�F���q��
		 * 
		 * 
		 */
		
		/**
		 * ��X�ۤv�����v�q��
		 * �C�@���U�泣�|��q��s���s�_��
		 * 
		 */

}
