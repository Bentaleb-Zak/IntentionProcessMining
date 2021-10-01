package com.models;
import java.util.ArrayList;
import java.util.List;

public class DBInstance {

	
	private int length;
	private int intentionLength;
	private int actionslength;
	
	private List<Row> instances;
	private List<Intention> intentions;
	private List<Activity> actions;
	
	DBInstance(int intentionLength, int actionslength){
		this.length = 0;
		this.intentionLength = intentionLength;
		this.actionslength = actionslength;
		
		this.instances = new ArrayList<Row>();
		this.intentions = new ArrayList<Intention>();
		this.actions = new ArrayList<Activity>();
	}
	
	public void addInstance(Row instance) {
		this.instances.add(instance);
		this.length++;
	}
	
	public void addIntention(Intention i) {
		this.intentions.add(i);
	}
	
	public void addAction(Activity a) {
		this.actions.add(a);
	}
	
	public Activity getActivity(String a) {
		
		if(actions.isEmpty()) return null;
		
		for(Activity activity: actions) {
			if(activity.getValue().equals(a)) return activity;
		}
		return null;
	}
	
	public void printIntentions() {
		for(Intention i : intentions) {
			//System.out.print("intention " + i.getIndex());
			System.out.println(i.toString());
		}
	}
	
	public void printActions() {
		for(Activity a : actions) {
			System.out.println(a.toString());
		}
	}
	
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getIntentionLength() {
		return intentionLength;
	}
	public void setIntentionLength(int intentionLength) {
		this.intentionLength = intentionLength;
	}
	public int getActionslength() {
		return actionslength;
	}
	public void setActionslength(int actionslength) {
		this.actionslength = actionslength;
	}
	public List<Row> getInstances() {
		return instances;
	}
	public void setInstances(List<Row> instances) {
		this.instances = instances;
	}
	public List<Intention> getIntentions() {
		return intentions;
	}
	public void setIntentions(List<Intention> intentions) {
		this.intentions = intentions;
	}
	public List<Activity> getActions() {
		return actions;
	}
	public void setActions(List<Activity> actions) {
		this.actions = actions;
	}
	
	
	
	
}
