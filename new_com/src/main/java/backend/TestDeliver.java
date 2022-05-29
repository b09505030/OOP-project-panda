package backend;

public class TestDeliver {

	public static void main(String[] args) {
		String  jdbcUrl = "jdbc:sqlite:userdb.db";
		Deliver DeliverAccount = new Deliver("b09505028","b09505028","0988233936","b09505028@gmail.com","Billy");
		System.out.println(DeliverAccount);
		DeliverAccount.storeToDB(jdbcUrl);

	}

}
