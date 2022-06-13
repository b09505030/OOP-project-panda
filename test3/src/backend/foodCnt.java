package backend;

import org.json.JSONObject;

public class foodCnt {
	public menuItem food;
	public int cnt;

	public foodCnt(menuItem food, int cnt) {
		super();
		this.food = food;
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		JSONObject tmp = new JSONObject();
		tmp.put("food", food.toString());
		tmp.put("cnt", cnt);		
		return tmp.toString();
	}

}
