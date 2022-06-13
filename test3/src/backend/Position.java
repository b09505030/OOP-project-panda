package backend;

import org.json.JSONObject;

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
			JSONObject tmp = new JSONObject();
			tmp.put("address", address);
			tmp.put("latitude", latitude);
			tmp.put("longitude", longitude);
			return tmp.toString();
		}
		public static Position FromJsontoObject(JSONObject tmp) {
			Position obj =new Position(tmp.getString("address"), tmp.getDouble("latitude"), tmp.getDouble("latitude")); 
			return obj;
		}
		
	}
