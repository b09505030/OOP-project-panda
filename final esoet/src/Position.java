public class Position {
		public String address;
		public float latitude,longitude;
		public Position(String address, float latitude, float longitude) {
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
