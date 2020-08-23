package org.sapient.training;
import java.util.*;
import java.io.*;

public class Runner {
	static DateTime obj = new DateTime();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String opt;
		//History.getHistory();
		History.getHistoryJSON();
		do {
			ArrayList<String> options = new ArrayList<String>(Arrays.asList("INPUT DATE",
					"ADDING DATES","SUBTRACTING DATES",
					"NATURAL LANGUAGE STATEMENT","READ HISTORY"));
			
			System.out.println("\n 1 To input date"
					+ "\n 2 For Adding Dates"
					+ "\n 3 For Subtracting Dates"
					+ "\n 4 For natural language statement"
					+ "\n 5 To Read History "
					+ "\n 6 For Adding n weeks "
					+ "\n 7 for Adding n months "
					+ "\n 8 for adding n years"
					+ "\n 9 For Adding n days "
					+ "\n 10 for getting current week"
					+ "\n");
			
			Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
			String str= sc.nextLine();
			int option = Integer.parseInt(str);
			String res = null;
			String input = null;
			
			switch(option){
				case 1:
					System.out.println("Input Date in format dd-mm-yyyy");
					input= sc.nextLine();
					obj.set(input);
					break;
				case 2:
					System.out.println("Input Dates in format dd-mm-yyyy");
					input= sc.nextLine();
					String[] dates = input.split(" ");
					DateTime obj1 = new DateTime(dates[0]);
					DateTime obj2 = new DateTime(dates[1]);
					res = DateTime.addDates(obj1.get(),obj2.get());
					System.out.println(res);
					break;
				case 3:
					System.out.println("Input Dates in format dd-mm-yyyy");
					input= sc.nextLine();
					dates = input.split(" ");
					DateTime obj3 = new DateTime(dates[0]);
					DateTime obj4 = new DateTime(dates[1]);
					res = DateTime.minusDates(obj3.get(),obj4.get());
					System.out.println(res);
					break;
				case 4:
					System.out.println("Enter statement");
					input= sc.nextLine();
					res = obj.naturalLangDate(input);
					System.out.println(res);
					break;
				case 5:
					History.readHistory();
					break;
				case 6:
					System.out.println("Enter n");
					input= sc.nextLine();
					res = obj.addNWeeks(Integer.parseInt(input));
					System.out.println(res);
					break;
				case 7:
					System.out.println("Enter n");
					input= sc.nextLine();
					res = obj.addNMonths(Integer.parseInt(input));
					System.out.println(res);
					break;
				case 8:
					System.out.println("Enter n");
					input= sc.nextLine();
					res = obj.addNYears(Integer.parseInt(input));
					System.out.println(res);
					break;
				case 9:
					System.out.println("Enter n");
					input= sc.nextLine();
					res = obj.addNDays(Integer.parseInt(input));
					System.out.println(res);
					break;
				case 10:
					res = obj.getWeek().toString();
					System.out.println(res);
					break;
				default:
					System.out.println("Please Choose again");
			}
			History.add(options.get(option-1),input,res);
			
			System.out.println("Do you want to continue(y/n)");
			opt = sc.nextLine();
		
		} while(opt.contains("y"));
		
		History.saveToFile();
		History.saveToJSON();
	}
}
