package org.sapient.parallelize;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.sapient.options.Options;
import org.sapient.training.DateTime;
import org.sapient.training.History;
import org.sapient.transaction.Transaction;


public class ParallelRunner {
	static DateTime obj = new DateTime();
	
	public static Transaction processInput(Transaction transaction) {
		String res = null;
		String input = transaction.getInput();
		Options option = transaction.getOption();
		switch(option){
			case ADD_NWEEKS:
				res = obj.addNWeeks(Integer.parseInt(input));
				break;
			case ADD_NMONTHS:
				res = obj.addNMonths(Integer.parseInt(input));
				break;
			case ADD_NYEARS:
				res = obj.addNYears(Integer.parseInt(input));
				break;
			case ADD_NDAYS:
				res = obj.addNDays(Integer.parseInt(input));
				break;
			case MINUS_NWEEKS:
				res = obj.minusNWeeks(Integer.parseInt(input));
				break;
			case MINUS_NMONTHS:
				res = obj.minusNMonths(Integer.parseInt(input));
				break;
			case MINUS_NYEARS:
				res = obj.minusNYears(Integer.parseInt(input));
				break;
			case MINUS_NDAYS:
				res = obj.minusNDays(Integer.parseInt(input));
				break;
			case WEEK_OF_YEAR:
				res = obj.getWeek().toString();
				break;
			case DAY_OF_WEEK:
				res = obj.getDayOfWeek();
				break;
			default:
				System.out.println("Please Choose again");
		}
		History.add(option.toString(),input,res);
		transaction.setResult(res);
		return transaction;
	}
	

	public static void main(String args[]) throws InterruptedException,ExecutionException {
		// TODO Auto-generated method stub
			Random rand = new Random(); 
		  
            List<Transaction> objects = new ArrayList<Transaction>();
	        for (int i = 0; i < 100000; i++) {
	            objects.add(new Transaction(Integer.toString(rand.nextInt(20)),Options.getRandom()));
	        }

	        List<Callable<Transaction>> tasks = new ArrayList<Callable<Transaction>>();
	        for (final Transaction object : objects) {
	            Callable<Transaction> c = new Callable<Transaction>() {
	                @Override
	                public Transaction call() throws Exception {
	                    return compute(object);
	                }
	            };
	            tasks.add(c);
	        }

	        ExecutorService exec = Executors.newCachedThreadPool();
	        // some other exectuors you could try to see the different behaviours
	        // ExecutorService exec = Executors.newFixedThreadPool(3);
	        // ExecutorService exec = Executors.newSingleThreadExecutor();
	        try {
	            long start = System.currentTimeMillis();
	            List<Future<Transaction>> results = exec.invokeAll(tasks);
	            int sum = 0;
	            for (Future<Transaction> fr : results) {
	                System.out.println(fr.get().getResult() + " " + fr.get().getInput() + " " + fr.get().getOption());
	            }
	            long elapsed = System.currentTimeMillis() - start;
	            System.out.println(String.format("Elapsed time: %d ms", elapsed));
	            System.out.println(String.format("... but compute tasks waited for total of %d ms; speed-up of %.2fx", sum, sum / (elapsed * 1d)));
	        } finally {
	            exec.shutdown();
	        }
	}

	protected static Transaction compute(Transaction object) {
		ParallelRunner.processInput(object);
		return object;
	}

};
