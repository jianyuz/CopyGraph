package enumoperation;

public enum BasicOperation implements Operation{
	PLUS("+"){
		@Override
		public double apply(double x, double y) {
			return x + y;
		}
	}, 
	MINUS("-"){

		@Override
		public double apply(double x, double y) {
			// TODO Auto-generated method stub
			return x-y;
		}
		
	};

	private final String symbol;
	BasicOperation(String symbol){
		this.symbol = symbol;
	}
	
	

}
