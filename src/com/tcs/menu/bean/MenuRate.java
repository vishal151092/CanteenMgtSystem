package com.tcs.menu.bean;

public class MenuRate 
{
	private int count;
	private double average;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	
	@Override
	public String toString() {
		return "MenuRate [count=" + count + ", average=" + average + "]";
	}
	
	

}
