package backend1;

/**
 * class to store Business time from mon to sun 
 * for each day start and end are included
 * @author b09505028
 *
 */
public class Business_Time {
	public BusDay mon;
	public BusDay tue;
	public BusDay wed;
	public BusDay thu;
	public BusDay fri;
	public BusDay sat;
	public BusDay sun;
	public static class BusDay{
		public String start;
		public String end;

		public BusDay(String start, String end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "start=" + start + ", end=" + end;
		}
	}

	public Business_Time(BusDay mon, BusDay tue, BusDay wed, BusDay thu, BusDay fri, BusDay sat, BusDay sun) {
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
	}

	@Override

	public String toString() {
		return "Business_Time [mon=" + mon + ", tue=" + tue + ", wed=" + wed + ", thu=" + thu + ", fri=" + fri
				+ ", sat=" + sat + ", sun=" + sun + "]";
	}

}
