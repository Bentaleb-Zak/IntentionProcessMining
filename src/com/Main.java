package com;


import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

import com.scoring.Scoring;
import com.testing.Testing;
import com.training.Training;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;


public class Main {


	public static void main(String[] args) {

		/** Create the parameters of the first Hidden Markov Model **/
		int n = 2;
		int m = 3;
		int l = 2;
		
		double[] pi = {0.33, 0.67};
		double[][] t = {{0.67, 0.33}, {0.67, 0.33}};
		double[][] e = {{ 0.25, 0.75 }, { 0.6, 0.4 }};
		List<ObservationInteger> oseq = new ArrayList<ObservationInteger>();
		
		/** Initialize the observation sequence **/
		for(int i=0; i< 4; i++) {
			oseq.add(new ObservationInteger(i%2));
		}
		
		/** Training module **/
		System.out.println("********** Training Result **********");
		Training training = new Training(n, m, l);
		
		training.setPi(pi);
		training.setT(t);
		training.setE(e);
		
		Hmm<ObservationInteger> trainHmm = training.train(30, 5.0E-7);
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
		
		/** Scoring module **/
		System.out.println("********** Scoring Result **********");
		Scoring scoring = new Scoring(trainHmm, oseq);
		
		System.out.println("Scoring probability : " + scoring.probability() + "\n");
		
		
		/** Testing module **/
		System.out.println("********** Testing Result **********");
		Testing testing = new Testing(trainHmm, oseq);
		
		System.out.println("The most likely state sequence is :");
		int[] stateSequence = testing.stateSequence();
		for(int j=0; j < stateSequence.length; j++)
			System.out.print(stateSequence[j] + " \t ");
		
	}

}
