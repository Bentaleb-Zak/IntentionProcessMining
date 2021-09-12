package com.models;

public class Context {

	private int index;  // index of the context in the Hmm model
	private String value; // value of the context

	Context(int index, String value){
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
