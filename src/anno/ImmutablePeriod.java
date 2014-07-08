package anno;

import java.util.Date;

public class ImmutablePeriod {

	private final Date start;//or use long to replace
	private final Date end;
	
	public ImmutablePeriod(Date start, Date end){
		//defensive copy first 
		//avoid check timing issue.
		//could be checked first then changed later.
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());
		if(this.start.compareTo(this.end)>0){
			throw new IllegalArgumentException("wrong arguments");
		}
	}
	
	public Date start(){
		return new Date(start.getTime()); //or use clone.
	}
}
