package com.models;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DBCreation {

	private String fileName;
	
	
	public DBCreation(String fileName){
		this.fileName = fileName;
	}


	public DBInstance readDatabase() {
		DBInstance db = new DBInstance(3, 14);

		
		try {
			Scanner scanner = new Scanner(new File(fileName));
			String line[];
			
			Intention intention1 = new Intention(0, "Before the trip");
			Intention intention2 = new Intention(1, "During the trip");
			Intention intention3 = new Intention(2, "After the trip");
			
			db.addIntention(intention1);
			db.addIntention(intention2);
			db.addIntention(intention3);
			
			line = scanner.nextLine().split(";");
			int i;
			
			Row instance = new Row();
			// Read contexts
			instance.setSexe(new Context(0, line[0]));
			instance.setOccupation(new Context(1, line[1]));
			instance.setAge(new Context(2, line[2]));
			
			// Read actions for intention 1
			IntentionActions actions1 = new IntentionActions();
			actions1.setIntention(intention1);
			for(i = 3; i < 8; i++) {
				Activity a = new Activity(i-3, line[i]);
				actions1.addAction(a);
				db.addAction(a);
			}
			
			// Read actions for intention 2
			IntentionActions actions2 = new IntentionActions();
			actions2.setIntention(intention2);
			for(i = 8; i < 13; i++) {
				Activity a = new Activity(i-3, line[i]);
				actions2.addAction(a);
				db.addAction(a);
			}
			// Read actions for intention 3
			IntentionActions actions3 = new IntentionActions();
			actions3.setIntention(intention3);
			for(i = 13; i < 17; i++) {
				Activity a = new Activity(i-3, line[i]);
				actions3.addAction(a);
				db.addAction(a);
			}
			
			instance.setIntention1(actions1);
			instance.setIntention2(actions2);
			instance.setIntention3(actions3);
			
			db.addInstance(instance);
			
			// -----------------------
			
			while(scanner.hasNext()) {
				line = scanner.nextLine().split(";");
				
				Row userinstance = new Row();
				// Read contexts
				instance.setSexe(new Context(0, line[0]));
				instance.setOccupation(new Context(1, line[1]));
				instance.setAge(new Context(2, line[2]));
				
				// Read actions for intention 1
				IntentionActions activities1 = new IntentionActions();
				activities1.setIntention(intention1);
				for(i = 3; i < 8; i++) {
					Activity a = db.getActivity(line[i]);
					activities1.addAction(a);
				}
				
				// Read actions for intention 2
				IntentionActions activities2 = new IntentionActions();
				activities2.setIntention(intention2);
				for(i = 8; i < 13; i++) {
					Activity a = db.getActivity(line[i]);
					activities2.addAction(a);
				}
				// Read actions for intention 3
				IntentionActions activities3 = new IntentionActions();
				activities3.setIntention(intention3);
				for(i = 13; i < 17; i++) {
					Activity a = db.getActivity(line[i]);
					activities3.addAction(a);
				}
				
				userinstance.setIntention1(activities1);
				userinstance.setIntention2(activities2);
				userinstance.setIntention3(activities3);
				
				db.addInstance(instance);

			}
			//System.out.println(length);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return db;
	}
	
	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
