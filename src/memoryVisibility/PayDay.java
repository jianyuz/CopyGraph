package memoryVisibility;

public enum PayDay {

	MONDAY(PayType.WEEKDAY),
	TUESDAY(PayType.WEEKDAY),
	WEDNESDAY(PayType.WEEKDAY),
	THURSDAY(PayType.WEEKDAY),
	FRIDAY(PayType.WEEKDAY),
	SATURDAY(PayType.WEEKEND),
	SUNDAY(PayType.WEEKEND);
	
	private static final int REGULAR_HOURS = 8;
	private PayType type;
	PayDay(PayType type){
		this.type = type;
	}
	
	public double calculatePay(int hoursWorked, double payRate){
		return type.calculatePay(hoursWorked, payRate);
	}
	
	enum PayType{
		WEEKDAY {
			@Override
			double overtimePay(int hoursWorked, double payRate) {
				
				return hoursWorked <= REGULAR_HOURS? 0: (hoursWorked - REGULAR_HOURS) * payRate /2 ;
			}
		},
		WEEKEND {
			@Override
			double overtimePay(int hoursWorked, double payRate) {
				// TODO Auto-generated method stub
				return hoursWorked * payRate /2;
			}
		};
		
		abstract double overtimePay(int hoursWorked, double payRate);
		
		public double calculatePay(int hoursWorked, double payRate){
			return payRate * REGULAR_HOURS + overtimePay(hoursWorked, payRate);
		}
	}
	
	public static void main(String[] args){
		System.out.println(SATURDAY.calculatePay(10, 10));
		System.out.println(TUESDAY.calculatePay(10, 10));
	}
}
