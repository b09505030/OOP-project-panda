
public class Query {
	String From, To;
	String TripType; 	//{ "單程", "去回程" }
	String TicketType;//{ "無", "早鳥", "大學生" }
	String StartDate, EndDate;
	String StartTime, EndTime;
	public Query() {
		From = "南港";
		To = "南港";
		TripType = "單程";
		TicketType = "無";
	}
}
