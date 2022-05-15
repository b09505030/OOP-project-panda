
public class Order {
	String From, To;
	String TripType; 	//{ "單程", "去回程" }
	String TicketType;//{ "無", "早鳥", "大學生" }
	String StartDate, EndDate;
	String StartTime, EndTime;
	String SeatType;	//{"標準車廂", "商務車廂"}
	int[] TicketCount; // {"全票", "早鳥","大學生"}
	public Order() {
		From = "南港";
		To = "南港";
		TripType = "單程";
		TicketType = "無";
		TicketCount = new int[3];
		TicketCount[0]=0;
		TicketCount[1]=0;
		TicketCount[2]=0;
		SeatType = "標準車廂";	
	}
}
