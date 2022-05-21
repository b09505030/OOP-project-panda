
public class Business_Time {
	public BusDay mon;
	public BusDay tue;
	public BusDay wed;
	public BusDay thu;
	public BusDay fri;
	public BusDay sat;
	public BusDay sun;
	public class BusDay{
		public String start;
		public String end;
		@Override
		public String toString() {
			return "BusDay [start=" + start + ", end=" + end + "]";
		}
	}@Override
	public String toString() {
		return "Business_Time [mon=" + mon + ", tue=" + tue + ", wed=" + wed + ", thu=" + thu + ", fri=" + fri
				+ ", sat=" + sat + ", sun=" + sun + "]";
	}
	
}
