package com.models;
import java.util.ArrayList;
import java.util.List;

public class IntentionActions {

	private Intention intention;
	private List<Activity> actions;
	private int length;
	
	IntentionActions(){
		this.length = 0;
		actions = new ArrayList<Activity>();
	}
	
	public void addAction(Activity action) {
		actions.add(action);
		this.length++;
	}
	
	
	
	public Intention getIntention() {
		return intention;
	}
	public void setIntention(Intention intention) {
		this.intention = intention;
	}
	public List<Activity> getActions() {
		return actions;
	}
	public void setActions(List<Activity> actions) {
		this.actions = actions;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
	
}
