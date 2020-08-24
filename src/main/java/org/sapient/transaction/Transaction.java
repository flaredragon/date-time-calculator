package org.sapient.transaction;

import java.util.HashMap;

import org.sapient.options.Options;

public class Transaction {
	String input;
	String result;
	Options option;
	
	Transaction(String input,String options)
	{
		this.option = Enum.valueOf(Options.class, options);
		this.input = input;
	}
	
	public Transaction(String input,Options options)
	{
		this.option = options;
		this.input = input;
	}
	
	
	public String getInput() {
		return this.input;
	}
	
	public Options getOption() {
		return this.option;
	}
	
	public String getResult() {
		return this.result;
	}
	
	
	public void setResult(String res) {
		this.result = res ;
	}
	
}
