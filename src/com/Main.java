package com;


import java.util.List;

import org.jfree.ui.RefineryUtilities;

import com.models.DBCreation;
import com.models.DBInstance;
import com.models.Row;
import com.scoring.Scoring;
import com.testing.Testing;
import com.training.Training;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;


public class Main {


	public static void main(String[] args) {


		DBCreation dbcreation = new DBCreation("C:\\Users\\zakab\\eclipse-workspace\\ContextSensitiveML_v0\\src\\com\\formulaire.csv");
		
		DBInstance db = dbcreation.readDatabase();
		
		System.out.println("\n################ Reading the database from the csv file ################\n");
		
		System.out.println("****************** INTENTIONS ******************");
		db.printIntentions();
		
		System.out.println("******************   ACTIONS  ******************");
		db.printActions();
		
		
		// Split data into train and test
		
		List<Row> rows = db.getInstances();
		
		List<Row> trainrows = rows.subList(0, (rows.size() * 9 ) / 10);
		List<Row> testrows = rows.subList((rows.size() * 9 ) / 10, rows.size());
		
		// Initialize HMM parameters
		
		int n = 3;
		int m = 14;
		int l = 5;
		
		double[] pi = {0.6, 0.2, 0.2};
		double[][] t = {{0.5, 0.4, 0.1}, {0.2, 0.5, 0.3}, {0.4, 0.1, 0.5}};
		double[][] e = {{ 0.18, 0.18, 0.18, 0.18, 0.18, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01 },
				{ 0.01, 0.01, 0.01, 0.01, 0.01, 0.18, 0.18, 0.18, 0.18, 0.18, 0.01, 0.01, 0.01, 0.01 },
				{ 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.18, 0.18, 0.18, 0.18 }};
		
		System.out.println("\n################ Training Phase ################\n");
		
		System.out.println("********** Training Result **********");
		
		Training training = new Training(n, m, l);
		
		training.setPi(pi);
		training.setT(t);
		training.setE(e);
		
		
		training.setTrainRows(trainrows);
		System.out.println("*** Before training :");
		System.out.println(training.buildHmm().toString());
		
		Hmm<ObservationInteger> trainHmm = training.train(30, 5.0E-7, true);
		System.out.println("*** After training :");
		System.out.println(trainHmm.toString());
		
		double[] distancehist = training.getDistancehist();
		int[] x = new int[distancehist.length];
		for(int i=0; i<distancehist.length; i++) {
			x[i] = i+1;
		}
		
		// Plot distance graph with JFreeChart 
		LineChart_AWT chart = new LineChart_AWT(
		         "Convergence" ,
		         "Iterations",
		         "Distance",
		         "Distance vs Iterations",
		         x,
		         distancehist,
		         distancehist.length);

		chart.pack( );
		RefineryUtilities.centerFrameOnScreen( chart );
		chart.setVisible( true );
		
		System.out.println("\n################ Scoring Phase ################\n");
		
		List<List<ObservationInteger>> scoringSequences = training.generateSequencesfromDB(trainrows);
		Scoring scoring = new Scoring(trainHmm);
		for(int i = 0; i < scoringSequences.size(); i++) {
			System.out.println("Scoring probability for sequence " + i + " : " + scoring.probability(scoringSequences.get(i)));
		}
		
		System.out.println("\n################ Testing Phase ################\n");
		
		List<List<ObservationInteger>> testSequences = training.generateSequencesfromDB(testrows);
		Testing testing = new Testing(trainHmm);
		for(int i = 0; i < testSequences.size(); i++) {
			int[] stateSequence = testing.stateSequence(testSequences.get(i));
			System.out.print("The most likely state sequence for sequence " + i + " : ");
			for(int j=0; j < stateSequence.length; j++)
				System.out.print(stateSequence[j] + " \t ");
			System.out.println("");
			
		}
		
		
		
	}

}
