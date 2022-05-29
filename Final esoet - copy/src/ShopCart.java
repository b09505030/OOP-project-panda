import java.util.ArrayList;
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

	/**
	 * class store food and amount
	 * 
	 * EX: noodle 140 *3 �q�j�Q�� 140 3��
	 */
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
			return "[food=" + food + ", cnt=" + cnt + "]";
		}

	}

	public ShopCart(String buyer,boolean VIP,Position destination,Restaurant rt) {
		super();
		this.buyer = buyer;
		this.VIP   = VIP;
		this.destination = destination;
		this.rt = rt;
		this.foodList = new ArrayList<foodCnt>();
	}

	@Override
	public String toString() {
		return "ShopCart [buyer=" + buyer + ", foodList=" + foodList + ",rt=" + rt + "]";
	}
	public void calcuTotal() {
		double Total = 0;
		//�C����18�� �g�n�ת��u�t���⦨���� �|�ˤ��J����
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
		//���B�馩������
		//���]�w���ܴN�ˬd �S�]�w�N���ˬd
		//���B ��������
		if (this.rt.getPriceForDiscount()!=0&&this.rt.getDiscount()!=0) {
			if (foodprice>=rt.getPriceForDiscount()) {
				foodprice=foodprice*rt.getDiscount();
			}
		}
		//VIP ���B���ιB�O
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
	 * �q�\�U����Ƹ̭���X���� �M��̼ƶq �[�JShopCart��foodList get food for the Restaurant object add
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
		System.out.println("����" + wantremove);
		for (foodCnt A : this.foodList) {
			if (A.food.name.equals(wantremove)) {
				this.foodList.remove(A);
				this.calcuTotal();
				break;
			}
		}
		System.out.println("\n");

	}

}