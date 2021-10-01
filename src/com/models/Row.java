package com.models;
public class Row {

	private Context age;
	private Context sexe;
	private Context occupation;
	
	// list of intentions
	private IntentionActions intention1;
	private IntentionActions intention2;
	private IntentionActions intention3;
	
	Row(){
		
	}
	
	public Context getAge() {
		return age;
	}
	public void setAge(Context age) {
		this.age = age;
	}
	public Context getSexe() {
		return sexe;
	}
	public void setSexe(Context sexe) {
		this.sexe = sexe;
	}
	public Context getOccupation() {
		return occupation;
	}
	public void setOccupation(Context occupation) {
		this.occupation = occupation;
	}
	public IntentionActions getIntention1() {
		return intention1;
	}
	public void setIntention1(IntentionActions intention1) {
		this.intention1 = intention1;
	}
	public IntentionActions getIntention2() {
		return intention2;
	}
	public void setIntention2(IntentionActions intention2) {
		this.intention2 = intention2;
	}
	public IntentionActions getIntention3() {
		return intention3;
	}
	public void setIntention3(IntentionActions intention3) {
		this.intention3 = intention3;
	}
	
	
}
