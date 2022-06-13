package backend;

public class TestDeliver {

	public static void main(String[] args) {
		String  jdbcUrl = "jdbc:sqlite:userdb.db";
		Deliver DeliverAccount = new Deliver("b09505030","b09505030","0988","b09505030@gmail.com","張");
		System.out.println(DeliverAccount);
		DeliverAccount.storeToDB(jdbcUrl);

	}

}
