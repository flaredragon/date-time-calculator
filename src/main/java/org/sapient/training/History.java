package org.sapient.training;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.json.*;

public class History implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String operation;
	private String input;
	private String result;
	private static Queue<History> history  = new CircularFifoQueue<History>(100);
	
	public History(String operation,String input, String result){
		super();
		this.operation = operation;
		this.result = result == null ? "": result;
		this.input = input == null ? "": input;
	}
	
	public static void add(String operation, String input ,String result) {
		History oper = new History(operation,input,result);
		history.add(oper);
	}
	
	@Override
    public String toString() {
        return "Employee [operation=" + operation + ", input=" + input +", result=" + result + "]";
    }
	
	public static void readHistory() {
		for(History calc:history) {
			System.out.println(calc);
		}
	}
	
	public static void saveToFile() {
		try {
            FileOutputStream fos = new FileOutputStream("dateData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(history);
            oos.close();
            fos.close();
        } 
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
	}

	public JSONObject getJSONObject() {
	    JSONObject obj = new JSONObject();
	    try {
	    	obj.put("operation", operation);
			obj.put("result", result);
			obj.put("input", input);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return obj;	
	}
	 
	public static void saveToJSON() {
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for(History item : history){
			jsonArray.put(item.getJSONObject());
		}
		
		try {
			obj.put("history", jsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try (FileWriter file = new FileWriter("dateData.json")) {	 
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
		//System.out.println(obj);
	}
	
	public static void getHistoryJSON() {
		try {
			File initialFile = new File("dateData.json");
			InputStream is = new FileInputStream(initialFile);
	        JSONTokener tokener = new JSONTokener(is);
	        JSONObject obj = new JSONObject(tokener);
	        //Read JSON file
            try {
            	System.out.println(obj);
            	JSONArray historyJSON = obj.getJSONArray("history");
				System.out.println(historyJSON);
				for(int i=0;i<historyJSON.length();i++) {
					JSONObject calc = historyJSON.getJSONObject(i);
					History n = new History(calc.getString("operation"),calc.getString("input"),calc.getString("result"));
					history.add(n);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void getHistory() {
		ArrayList<History> employees = new ArrayList<History>();
        try {
            FileInputStream fis = new FileInputStream("dateData");
            ObjectInputStream ois = new ObjectInputStream(fis);
            employees = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            history.addAll(employees);
        } 
        catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } 
        catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
	}
	
}
