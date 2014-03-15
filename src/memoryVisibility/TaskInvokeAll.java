package memoryVisibility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TaskInvokeAll {
	ExecutorService executorService = Executors.newFixedThreadPool(2);
	
	public List<TravelQuote> getRankedTravelQuote(TravelInfo travelInfo,
			Set<TravelCompany> companies, 
			Comparator<TravelQuote> ranking, long time, TimeUnit timeUnit) throws InterruptedException{
		
		List<QuoteTask> tasks = new ArrayList<QuoteTask>();
		for(TravelCompany company: companies){
			tasks.add(new QuoteTask(company, travelInfo));
		}
		
		List<Future<TravelQuote>> fs = executorService.invokeAll(tasks, time, timeUnit);
		List<TravelQuote> quotes = new ArrayList<TravelQuote>(tasks.size());
		
		for(Future<TravelQuote> ff : fs){
			try {
				TravelQuote quote = ff.get();
				quotes.add(quote);
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				quotes.add(null);
			}catch(CancellationException e){
				quotes.add(null);
			}
		}
		
		Collections.sort(quotes, ranking);
		return quotes;
	}
}

class QuoteTask implements Callable<TravelQuote>{

	private final TravelCompany travelCompany;
	private final TravelInfo travelInfo;
	
	public QuoteTask(TravelCompany company, TravelInfo travelInfo){
		this.travelCompany = company;
		this.travelInfo = travelInfo;
	}
	
	@Override
	public TravelQuote call() throws Exception {
		// TODO Auto-generated method stub
		return new TravelQuote();
	}
	
}

class TravelCompany{
	
}
class TravelInfo{

}
class TravelQuote{
	
}
