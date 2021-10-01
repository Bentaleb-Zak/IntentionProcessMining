package com.models;

public class Activity {

	private int index;  // index of the activity in the Hmm model
	private String value; // value of the activity

	Activity(int index, String value){
		this.index = index;
		this.value = value;
	}
	
	public String toString() {
		return "Index : " + this.index +
				" Value : " + this.value;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
