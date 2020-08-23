package org.sapient.parallelize;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.sapient.training.DateTime;
import org.sapient.training.History;


public class ParallelRunner {
	static DateTime obj = new DateTime();
	
	public static String processInput(int option,String input) {
		String res = null;
		switch(option){
			case 1:
				obj.set(input);
				break;
			case 2:
				String[] dates = input.split(" ");
				DateTime obj1 = new DateTime(dates[0]);
				DateTime obj2 = new DateTime(dates[1]);
				res = DateTime.addDates(obj1.get(),obj2.get());
				System.out.println(res);
				break;
			case 3:
				dates = input.split(" ");
				DateTime obj3 = new DateTime(dates[0]);
				DateTime obj4 = new DateTime(dates[1]);
				res = DateTime.minusDates(obj3.get(),obj4.get());
				System.out.println(res);
				break;
			case 4:
				res = obj.naturalLangDate(input);
				System.out.println(res);
				break;
			case 5:
				History.readHistory();
			default:
				System.out.println("Please Choose again");
		}
		return res;
	}
	

	public static void main(String args[]) throws InterruptedException,ExecutionException {
		// TODO Auto-generated method stub
	        List<Integer> objects = new ArrayList<Integer>();
	        for (int i = 0; i < 100; i++) {
	            objects.add(50*i);
	        }

	        List<Callable<String>> tasks = new ArrayList<Callable<String>>();
	        for (final Integer object : objects) {
	            Callable<String> c = new Callable<String>() {
	                @Override
	                public String call() throws Exception {
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
	            List<Future<String>> results = exec.invokeAll(tasks);
	            int sum = 0;
	            for (Future<String> fr : results) {
	                System.out.println(fr.get());
	            }
	            long elapsed = System.currentTimeMillis() - start;
	            System.out.println(String.format("Elapsed time: %d ms", elapsed));
	            System.out.println(String.format("... but compute tasks waited for total of %d ms; speed-up of %.2fx", sum, sum / (elapsed * 1d)));
	        } finally {
	            exec.shutdown();
	        }
	}

	protected static String compute(Integer object) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(object);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "yooo";
	}

};
