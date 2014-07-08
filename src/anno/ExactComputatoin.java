package anno;

import java.math.BigDecimal;

public class ExactComputatoin {

//	public static void main(String[] args) {
//		double funds = 1.00;
//		int itemsBought = 0;
//		for (double price = .10; funds >= price; price += .10) {
//			funds -= price;
//			itemsBought++;
//		}
//		System.out.println(itemsBought + " items bought.");
//		System.out.println("Change: $" + funds);
//	}
	
//	public static void main(String[] args){
//		BigDecimal funds = new BigDecimal("1.00");
//		final BigDecimal tenCents = new BigDecimal("0.10");
//		int itemBought = 0;
//		for(BigDecimal price = tenCents; funds.compareTo(price) >=0; price = price.add(tenCents)){
//			funds = funds.subtract(price);
//			itemBought++;
//		}
//		System.out.println(itemBought + " items bought.");
//		System.out.println("Change: $" + funds);
//	}
	
	public static void main(String[] args){
		int funds = 100;
		int itemBought = 0;
		for(int price = 10; funds >= price; price += 10){
			funds = funds - price;
			itemBought ++;
		}
		System.out.println(itemBought + " item bought");
		System.out.println("change: $" + funds);
	}
	
}
