
public class Order {
	String From, To;
	String TripType; 	//{ "��{", "�h�^�{" }
	String TicketType;//{ "�L", "����", "�j�ǥ�" }
	String StartDate, EndDate;
	String StartTime, EndTime;
	String SeatType;	//{"�зǨ��[", "�ӰȨ��["}
	int[] TicketCount; // {"����", "����","�j�ǥ�"}
	public Order() {
		From = "�n��";
		To = "�n��";
		TripType = "��{";
		TicketType = "�L";
		TicketCount = new int[3];
		TicketCount[0]=0;
		TicketCount[1]=0;
		TicketCount[2]=0;
		SeatType = "�зǨ��[";	
	}
}
