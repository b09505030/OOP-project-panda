package backend;

//path jdbcUrl should be changed
//path jdbcUrl should be changed 
//path jdbcUrl should be changed 
//path jdbcUrl should be changed 
//path jdbcUrl should be changed 
//path jdbcUrl should be changed 
public class Test {

	public static void main(String[] args) {
		//path jdbcUrl should be changed 
		String  jdbcUrl = "jdbc:sqlite:userdb.db";
		Client MyAccount = new Client("Billy","b09505028","b09505028","0988233936","b09505028@gmail.com");

		System.out.println(MyAccount);
		MyAccount.MonthVIP();
		System.out.println(MyAccount);
		MyAccount.storeToDB(jdbcUrl);
		Client.getDB(jdbcUrl);
		

	}


	
	
	
	

}
