public class Position {
		public String address;
		public double latitude,longitude;
		public Position(String address, double latitude, double longitude) {
			super();
			this.address = address;
			this.latitude = latitude;
			this.longitude = longitude;
		}
		@Override
		public String toString() {
			return "Position [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
		}
		
	}
