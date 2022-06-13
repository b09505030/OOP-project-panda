package backend;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * class store food and amount
 *
 * EX: noodle 140 *3 義大利麵 140 3份
 */
class foodCnt {
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
public class ShopCart {
	public String 		buyer;
	public Position 	destination;
	public Restaurant 	rt;
	private boolean     VIP;
	private double      totalprice;
	private double      foodtotal;
	private double      gasfee;


	public double getTotalprice() {
		return totalprice;
	}

	/**
	 * List store food and amount ex: noodle 140 *3 soup 40 *2 rice 10 *1
	 *
	 */
	public ArrayList<foodCnt> 	foodList;





	public ShopCart(String buyer,boolean VIP,Position destination,Restaurant rt) {
		super();
		this.buyer = buyer;
		this.VIP   = VIP;
		this.destination = destination;
		this.rt = rt;
		this.foodList = new ArrayList<foodCnt>();
	}


	public ShopCart(String buyer, Position destination, Restaurant rt, boolean vIP, double totalprice, double foodtotal,
					double gasfee, ArrayList<foodCnt> foodList) {
		super();
		this.buyer = buyer;
		this.destination = destination;
		this.rt = rt;
		VIP = vIP;
		this.totalprice = totalprice;
		this.foodtotal = foodtotal;
		this.gasfee = gasfee;
		this.foodList = foodList;
	}


	public void calcuTotal() {
		double Total = 0;
		//每公里18元 經緯度直線差換算成公里 四捨五入到整數
		double dx,dy,gasfee;
		dx = (this.destination.latitude-this.rt.getPosition().latitude)*111;
		dy = (this.destination.longitude-this.rt.getPosition().longitude)*100;
		gasfee  = Math.round(18*Math.sqrt(dx*dx+dy*dy));
		//foodprcie
		double foodprice=0;
		for(foodCnt F:this.foodList) {
			Double FFPP=Double.parseDouble(F.food.price);
			foodprice+=F.cnt*FFPP.doubleValue();
		}
		//滿額折扣的部分
		//有設定的話就檢查 沒設定就不檢查
		//滿額 直接打折
		if (this.rt.getPriceForDiscount()!=0&&this.rt.getDiscount()!=0) {
			if (foodprice>=rt.getPriceForDiscount()) {
				foodprice=foodprice*rt.getDiscount();
			}
		}
		//VIP 滿額不用運費
		if(VIP&&foodprice>=180)gasfee=0;
		Total = gasfee+foodprice;
		this.totalprice=Total;
		this.foodtotal =foodprice;
		this.gasfee    = gasfee;
		System.out.println("gas"+gasfee);
		System.out.println("food"+foodprice);
		System.out.println(Total);
	}

	/**
	 * 從餐廳的資料裡面抓出食物 然後依數量 加入ShopCart的foodList get food for the Restaurant object add
	 * the food and amount to foodList in ShopCart
	 *
	 * @param whichfood the food that client selected
	 * @param amount    the amount that client selected
	 */

	public void addfood(String whichfood, int amount) {
		System.out.println(whichfood + "*" + amount);
		ArrayList<menuItem> menu = rt.getMenu();
		for (foodCnt e : this.foodList) {
			if (whichfood.equals(e.food.name)) {
				e.cnt=e.cnt+amount;
				this.calcuTotal();
				return;
			}
		}
		{
			menuItem thefood = null;
			for (menuItem e : menu) {
				if (whichfood.equals(e.name)) {
					thefood = e;
					break;
				}
			}
			this.foodList.add(new foodCnt(thefood, amount));
		}
		this.calcuTotal();
		System.out.println("\n");
		return;
	}

	public void removefood(String wantremove) {
		System.out.println("取消" + wantremove);
		for (foodCnt A : this.foodList) {
			if (A.food.name.equals(wantremove)) {
				this.foodList.remove(A);
				this.calcuTotal();
				break;
			}
		}
		System.out.println("\n");

	}

	@Override
	public String toString() {
		JSONObject tmp = new JSONObject();
		tmp.put("buyer", buyer);
		tmp.put("destination", new JSONObject(destination.toString()));
		tmp.put("rt", new JSONObject(rt.tojsonStr()));
		tmp.put("VIP", VIP);
		tmp.put("totalprice", totalprice);
		tmp.put("foodtotal", foodtotal);
		tmp.put("gasfee", gasfee);
		JSONArray foodList = new JSONArray();
		for(foodCnt fct:this.foodList) {
			JSONObject A=new JSONObject();
			JSONObject food=new JSONObject();
			food.put("name", fct.food.name);
			food.put("price", fct.food.price);
			A.put("food",food);
			A.put("cnt",fct.cnt);
			foodList.put(A);
		}
		tmp.put("foodList", foodList);
//		System.out.println("ttttttest"+tmp);
		return tmp.toString();
//		return "buyer=" + buyer + ", destination=" + destination + ", rt=" + rt.tojsonStr() + ", VIP=" + VIP
//				+ ", totalprice=" + totalprice + ", foodtotal=" + foodtotal + ", gasfee=" + gasfee + ", foodList="
//				+ foodList + "]";
	}
	public static ShopCart FromJsontoObject(JSONObject input) {
		String 		buyer=input.getString("buyer");

		Position 	destination=Position.FromJsontoObject(input.getJSONObject("destination"));
		System.out.println("0001"+input.getJSONObject("rt"));
		Restaurant 	rt=Restaurant.FromJsontoObject(input.getJSONObject("rt"));
		boolean     VIP=input.getBoolean("VIP");
		double      totalprice=input.getDouble("totalprice");
		double      foodtotal=input.getDouble("foodtotal");
		double      gasfee=input.getDouble("gasfee");
		System.out.println(input);
		ArrayList<foodCnt> 	foodList = new ArrayList<foodCnt>();
		for(int i=0;i<input.getJSONArray("foodList").length();i++) {
//			menuItem tmpitem = new menuItem(new JSONObject(input.getJSONArray("foodList").getJSONObject(i).getString("food")).getString("name"),new JSONObject(input.getJSONArray("foodList").getJSONObject(i).getString("food")).getString("price"));
			menuItem tmpitem = new menuItem(input.getJSONArray("foodList").getJSONObject(i).getJSONObject("food").getString("name"),input.getJSONArray("foodList").getJSONObject(i).getJSONObject("food").getString("price"));
			foodCnt tmp = new foodCnt(tmpitem,input.getJSONArray("foodList").getJSONObject(i).getInt("cnt") );
			foodList.add(tmp);
		}
		ShopCart A =new ShopCart(buyer,destination,rt,VIP,totalprice,foodtotal,
				gasfee, foodList);
		return A;


	}


}
